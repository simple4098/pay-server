package com.yql.biz.support.helper;

import com.yql.biz.client.IUserCenterClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IBankInfoDao;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.enums.IdentificationType;
import com.yql.biz.enums.RealNameAuthType;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.BankInfo;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.web.ResponseModel;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;

/**
 * <p>payServiceHelper具体实现</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Component
public class PayAccountServiceHelper implements IPayAccountServiceHelper{
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private IPayAccountDao payAccountDao;
    @Resource
    private OrderNoGenerator orderNoGenerator;
    @Resource
    private IBankInfoDao bankInfoDao;
    @Resource
    private IUserCenterClient userCenterClient;

    @Override
    public void md5PayPassword(PayAccount payAccount)  {
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        try{
            String base64 = payAccount.getPayPassword();
            byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
            String payPassword = new String(bytes);
            String md5PassWord = PayUtil.md5PassWord(payAccount.getRandomCode(), payPassword, passwordMd5Str);
            payAccount.setPayPassword(md5PassWord);
        }catch (Exception e){
            throw new RuntimeException("error.payserver.paypassword");
        }
    }

    @Override
    public void validateOldPassword(String password, PayAccount payAccount)  {
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        String md5PassWord = null;
        try{
            byte[] bytes = new BASE64Decoder().decodeBuffer(password);
            String payPassword = new String(bytes);
            md5PassWord = PayUtil.md5PassWord(payAccount.getRandomCode(), payPassword, passwordMd5Str);
        }catch (Exception e){
            throw new MessageRuntimeException("error.payserver.paypassword");
        }
        if (!payAccount.getPayPassword().equals(md5PassWord)){
            throw new MessageRuntimeException("error.payserver.validate.password");
        }
    }

    @Override
    public Param crateBangBankParam(PayBank newPayBak) {
        Request<BangBody> request = new Request<>();
        BankInfo byBankName = bankInfoDao.findByBankName(newPayBak.getBankName());
        PayAccount payAccount = payAccountDao.findByUserCode(newPayBak.getUserCode());
        String txSNBinding = orderNoGenerator.txSNBinding(newPayBak);
        String txCode = orderNoGenerator.txCode(newPayBak);
        newPayBak.setTxSNBinding(txSNBinding);
        newPayBak.setTxCode(txCode);
        newPayBak.setBankId(byBankName.getBankCode());
        Head head = new Head() ;
        head.setInstitutionID(applicationConf.getInstitutionId());
        head.setTxCode(txCode);
        request.setHead(head);
        BangBody bangBody = new BangBody();
        bangBody.setAccountName(newPayBak.getBankName());
        bangBody.setAccountNumber(newPayBak.getBankCard());
        bangBody.setIdentificationType(payAccount.getIdentificationType().getValue());
        bangBody.setBankId(byBankName.getBankCode());
        bangBody.setCardType(newPayBak.getCardType().getValue());
        bangBody.setPhoneNumber(newPayBak.getPhoneNumber());
        bangBody.setTxSNBinding(txSNBinding);
        bangBody.setIdentificationNumber(payAccount.getIdentificationNumber());
        bangBody.setValidDate(newPayBak.getValidDate());
        bangBody.setcVN2(newPayBak.getCvn2());
        request.setBody(bangBody);
        try {
            return  PlatformPayUtil.payRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PayAccount findOrCratePayAccount(String userCode) {
        PayAccount payAccount = payAccountDao.findByUserCode(userCode);
        if (payAccount == null){
            ResponseModel<UserBasicInfoVo> baseUserInfo = userCenterClient.getBaseUserInfo(userCode);
            if (baseUserInfo!=null && baseUserInfo.getData()!=null){
                UserBasicInfoVo baseUserInfoData = baseUserInfo.getData();
                payAccount = new PayAccount();
                payAccount.setUserCode(userCode);
                boolean weatherAuth = baseUserInfoData.getWeatherAuth() == 1;
                payAccount.setRealNameAuth(weatherAuth);
                if (weatherAuth){
                    RealNameAuthType authType = baseUserInfoData.getAuthType();
                    if (authType.equals(RealNameAuthType.MAINlAND)){
                        payAccount.setIdentificationType(IdentificationType.ID_CARD);
                    }else {
                        payAccount.setIdentificationType(IdentificationType.HKMACTW);
                    }
                }
                payAccount = payAccountDao.save(payAccount);
            }else {
                throw  new MessageRuntimeException("error.payserver.saveySecurity.userCode");
            }
        }
        return payAccount;
    }
}
