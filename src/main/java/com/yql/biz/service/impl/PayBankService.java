package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.service.IPayBankService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>支付银行卡Service</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
@Transactional
public class PayBankService implements IPayBankService {
    private static final Logger logger = LoggerFactory.getLogger(PayBankService.class);
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;
    @Resource
    private IPayAccountDao payAccountDao;


    @Override
    public ResultBangBank savePayBank(PayBankVo payBankVo) {
        PayBank newPayBak = new PayBank();
        ResultBangBank resultBangBank = payAccountServiceHelper.crateQuickBangBankParam(payBankVo,newPayBak);
        payBankDao.save(newPayBak);
        return resultBangBank;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayBankVo> findByUserCode(String userCode) {
        List<PayBank> list = payBankDao.findByUserCodeAndDeleted(userCode,false);
        return PayBankVo.domainToVoList(list);
    }

    @Override
    public ResultUnBangBank delBangBank(DelBankCardVo delBankCard) {
        PayAccount one = payAccountDao.findOne(delBankCard.getPayAccountId());
        //移除银行卡验证密码
        payPasswordSecurityHelper.validateOldPassword(delBankCard.getPayPassword(),one);
        ResultUnBangBank resultUnBangBank = new ResultUnBangBank();
        PayBank payBank = payBankDao.findByPayAccountIdAndTxCodeAndDeleted(delBankCard.getPayAccountId(),delBankCard.getTxCode() ,false);
        if (payBank == null) throw new MessageRuntimeException("error.payserver.payServer.bank.isnull");
        payBank.setDeleted(true);
        payBankDao.save(payBank);
        /* //解绑银行卡调用支付平台接口
        Param param = payAccountServiceHelper.crateUnBangBankParam(payBank);
        UninstallBangResponse response = payClient.uninstallBangBank(param.getMessage(),param.getSignature());
        if (PlatformPayUtil.isSuccess(response)){
            UninstallBangResponseBody responseBody = response.getUninstallBangResponseBody();
            logger.debug("解银行卡绑定返回:"+ JSON.toJSONString(response));
            payAccountServiceHelper.createDelBankParam(payBank,responseBody);
            payBankDao.save(payBank);
            BeanUtils.copyProperties(responseBody,resultUnBangBank);
            return resultUnBangBank;
        }else {
            throw new RuntimeException(response.getHead().getMessage());
        }*/
        return resultUnBangBank;
    }

    @Override
    public PayBankNumVo findBankNumByUserCode(String userCode) {
        List<PayBank> list = payBankDao.findByUserCodeAndDeleted(userCode,false);
        int num = 0;
        if (!CollectionUtils.isEmpty(list)){
            num = list.size();
        }
        return new PayBankNumVo(userCode,num);
    }
}
