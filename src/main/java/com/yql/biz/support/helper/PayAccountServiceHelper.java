package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IAccountClient;
import com.yql.biz.client.IFyCheckCardPayClient;
import com.yql.biz.client.IFyPayForClient;
import com.yql.biz.client.IUserCenterClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IBankInfoDao;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.enums.BankCodeType;
import com.yql.biz.enums.IdentificationType;
import com.yql.biz.enums.RealNameAuthType;
import com.yql.biz.enums.fy.FyRequestType;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.BankInfo;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.*;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.fy.CheckCardRequest;
import com.yql.biz.vo.pay.fy.CheckCardResponse;
import com.yql.biz.vo.pay.fy.FyPayForRequest;
import com.yql.biz.vo.pay.fy.FyPayRequest;
import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.request.UninstallBangBody;
import com.yql.biz.vo.pay.response.UninstallBangResponseBody;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>payServiceHelper具体实现</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Component
public class PayAccountServiceHelper implements IPayAccountServiceHelper{
    private static final Logger logger = LoggerFactory.getLogger(PayAccountServiceHelper.class);
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
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private IAccountClient accountClient;
    @Resource
    private IFyCheckCardPayClient fyCheckCardPayClient;

    @Override
    public Param crateQuickBangBankParam(PayBankVo payBankVo, PayBank newPayBak) {
        PayAccount payAccount = findOrCratePayAccount(payBankVo.getUserCode());
        if (!payAccount.isRealNameAuth()) throw new MessageRuntimeException("error.payserver.isRealNameAuth");
        PayBankVo.voToDomain(newPayBak,payBankVo, payAccount);
        PayBank payBank = payBankDao.findByPayAccountIdAndBankCardAndDeleted(payAccount.getId(), payBankVo.getBankCard(),false);
        if (payBank != null)  throw new MessageRuntimeException("error.payserver.paybankCard.repeat");
        List<PayBank> list = payBankDao.findByPayAccountIdOrderBySort(payAccount.getId());
        if (!CollectionUtils.isEmpty(list)) {
            newPayBak.setSort(list.size());
        }
        CheckCardRequest checkCardRequest = new CheckCardRequest(applicationConf.getFyMerid(),payBankVo.getBankCard(),payBankVo.getCardholder(),"0",payAccount.getIdentificationNumber(),payBankVo.getPhoneNumber());
        String md5String = checkCardRequest.toMd5String(applicationConf.getFyKey());
        checkCardRequest.setSign(md5String);
        String xml = PlatformPayUtil.payRequestXml(checkCardRequest);
        logger.debug("发送报文xml:"+xml);
        String checkCardResponse = fyCheckCardPayClient.checkCard(xml);
        CheckCardResponse cardResponse = (CheckCardResponse) PlatformPayUtil.convertXmlStrToObject(CheckCardResponse.class,checkCardResponse);
        Request<BangBody> request = new Request<>();
        String txSNBinding = orderNoGenerator.generateBankCode(newPayBak, BankCodeType.SN_BINDING);
        String txCode = orderNoGenerator.generateBankCode(newPayBak,BankCodeType.TX_CODE);
        String settlementFlag = orderNoGenerator.generateBankCode(newPayBak,BankCodeType.SETTLEMENTFLAG);
        payBank = payBankDao.findByPayAccountIdAndTxCodeAndDeleted(newPayBak.getPayAccountId(), newPayBak.getTxCode(),false);
        payBank = payBankDao.findByPayAccountIdAndTxSNBindingAndDeleted(newPayBak.getPayAccountId(), txSNBinding,false);
        payBank = payBankDao.findByPayAccountIdAndSettlementFlagAndDeleted(newPayBak.getPayAccountId(), settlementFlag,false);
        if (payBank !=null) throw new MessageRuntimeException("error.payserver.paybankCard.repeat");
        newPayBak.setTxSNBinding(txSNBinding);
        newPayBak.setTxCode(txCode);
        if (cardResponse!=null){
            newPayBak.setBankName(cardResponse.getCnm());
            newPayBak.setBankId(cardResponse.getInsCd());
        }
        newPayBak.setSettlementFlag(settlementFlag);
        Head head = new Head() ;
        head.setInstitutionID(applicationConf.getInstitutionId());
        head.setTxCode(txCode);
        request.setHead(head);
        try {
            return  PlatformPayUtil.payRequest(request);
        } catch (Exception e) {
            throw  new MessageRuntimeException("error.payserver.payServer.DJ");
        }
    }

    @Override
    public PayAccount findOrCratePayAccount(String userCode) {
        PayAccount payAccount = payAccountDao.findByUserCode(userCode);
        if (payAccount == null){
            payAccount = new PayAccount();
            ResponseModel<UserBasicInfoVo> baseUserInfo = userCenterClient.getBaseUserInfo(userCode);
            logger.debug("user-center client:"+JSON.toJSONString(baseUserInfo));
            if (baseUserInfo!=null && baseUserInfo.getData()!=null){
                UserBasicInfoVo baseUserInfoData = baseUserInfo.getData();
                payAccount.setUserCode(userCode);
                relNameInfo(payAccount,baseUserInfoData);
            }else {
                throw  new MessageRuntimeException("error.payserver.saveySecurity.userCode");
            }
        }
        return payAccount;
    }

    private void  relNameInfo(PayAccount payAccount,UserBasicInfoVo data){
        boolean weatherAuth = data.getWeatherAuth() == 1;
        payAccount.setRealNameAuth(weatherAuth);
        if (weatherAuth){
            RealNameAuthType authType = data.getAuthType();
            if (authType.equals(RealNameAuthType.MAINlAND)){
                payAccount.setIdentificationType(IdentificationType.ID_CARD);
            }else {
                payAccount.setIdentificationType(IdentificationType.HKMACTW);
            }
            payAccount.setIdentificationNumber(data.getIdCard());
            payAccountDao.save(payAccount);
        }
    }

    @Override
    public void updatePayAccountRelName(String userCode) {
        PayAccount payAccount = payAccountDao.findByUserCode(userCode);
        ResponseModel<UserBasicInfoVo> baseUserInfo = userCenterClient.getBaseUserInfo(userCode);
        if (baseUserInfo!=null){
            UserBasicInfoVo data = baseUserInfo.getData();
            relNameInfo(payAccount,data);
        }
    }

    @Override
    public Param crateUnBangBankParam(PayBank payBank) {
        Request<UninstallBangBody> request = new Request<>();
        String unBingDing = orderNoGenerator.generateBankCode(payBank, BankCodeType.TX_SN_UN_BINDING);
        payBank.setTxSNUnBinding(unBingDing);
        Head head = new Head() ;
        head.setInstitutionID(applicationConf.getInstitutionId());
        head.setTxCode(payBank.getTxCode());
        request.setHead(head);
        UninstallBangBody uninstallBangBody = new UninstallBangBody();
        uninstallBangBody.setTxSNBinding(payBank.getTxSNBinding());
        uninstallBangBody.setTxSNUnBinding(unBingDing);
        request.setBody(uninstallBangBody);
        try {
            return  PlatformPayUtil.payRequest(request);
        } catch (Exception e) {
            throw  new MessageRuntimeException("error.payserver.payServer.DJ");
        }
    }

    @Override
    public void validateSecurityParam(List<ProblemAnswerVo> problemAnswerVoList) {
        for (ProblemAnswerVo problemAnswerVo:problemAnswerVoList) {
            if (StringUtils.isEmpty(problemAnswerVo.getAnswer())){
                throw new MessageRuntimeException("error.payserver.saveySecurity.answer");
            }
            if (StringUtils.isEmpty(problemAnswerVo.getProblemId())){
                throw new MessageRuntimeException("error.payserver.saveySecurity.problem");
            }
        }
    }

    @Override
    public List<ProblemAnswerVo> getProblemList(String json) {
        if (StringUtils.isEmpty(json)) throw new MessageRuntimeException("error.payserver.param.notnull");
        SecurityVo securityVo = JSON.parseObject(json, SecurityVo.class);
        return securityVo.getAnswers();
    }

    @Override
    public void createDelBankParam(PayBank payBank, UninstallBangResponseBody responseBody) {
        Integer status = responseBody.getStatus();
        if (!status.equals(30)){
            payBank.setDeleted(true);
        }
        payBank.setBangStatus(status);
        payBank.setBankTxTime(responseBody.getBankTxTime());
    }

    @Override
    public void validateDrawMoney(PayOrderVo payOrderVo) {
        ResponseModel<AccountVo> account = accountClient.getAccount(payOrderVo.getUserCode());
        AccountVo data = account.getData();
        if (data==null){
           throw new MessageRuntimeException("error.payserver.account.cashFee");
        }else{
            BigDecimal cashFee = data.getCashFee();
            int i = payOrderVo.getTotalPrice().compareTo(cashFee);
            if (i==1){
                throw new MessageRuntimeException("error.payserver.account.balance");
            }
        }
    }


}
