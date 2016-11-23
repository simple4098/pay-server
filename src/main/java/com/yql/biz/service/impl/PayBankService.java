package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.PayClient;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayBank;
import com.yql.biz.service.IPayBankService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayBankVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PayClient payClient;


    @Override
    public PayBank savePayBank(PayBankVo payBankVo) {
        PayBank newPayBak = new PayBank();
        Param param = payAccountServiceHelper.crateBangBankParam(payBankVo,newPayBak);
        BangResponse response = payClient.bangBank(param.getMessage(), param.getSignature());
        if (PlatformPayUtil.isSuccess(response)){
            BangResponseBody bangResponseBody = response.getBangResponseBody();
            logger.debug("银行卡绑定返回:"+ JSON.toJSONString(response));
            newPayBak.setBangStatus(bangResponseBody.getStatus());
            newPayBak.setBankTxTime(bangResponseBody.getBankTxTime());
            return payBankDao.save(newPayBak);
        }else {
            throw new RuntimeException(response.getHead().getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayBankVo> findByUserCode(String userCode) {
        List<PayBank> list = payBankDao.findByUserCodeAndDeleted(userCode,false);
        return PayBankVo.domainToVoList(list);
    }

    @Override
    public void delBangBank(String txCode,Integer payAccountId) {
        PayBank payBank = payBankDao.findByPayAccountIdAndTxCodeAndDeleted(payAccountId,txCode ,false);
        if (payBank == null) throw new MessageRuntimeException("error.payserver.payServer.bank.isnull");
        Param param = payAccountServiceHelper.crateUnBangBankParam(payBank);
        UninstallBangResponse response = payClient.uninstallBangBank(param.getMessage(),param.getSignature());
        if (PlatformPayUtil.isSuccess(response)){
            UninstallBangResponseBody responseBody = response.getUninstallBangResponseBody();
            logger.debug("解银行卡绑定返回:"+ JSON.toJSONString(response));
            payBank.setDeleted(true);
            payBank.setBangStatus(responseBody.getStatus());
            payBank.setBankTxTime(responseBody.getBankTxTime());
            payBankDao.save(payBank);
        }else {
            throw new RuntimeException(response.getHead().getMessage());
        }
    }
}
