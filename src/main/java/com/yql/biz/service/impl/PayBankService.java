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
import com.yql.biz.vo.pay.response.Response;
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
    public PayBank savePayBanke(PayBankVo payBankVo) {
        PayBank newPayBak = new PayBank();
        // TODO: 2016/11/10 0010 调用第三方借口
        Param param = payAccountServiceHelper.crateBangBankParam(payBankVo,newPayBak);
        Response response = payClient.bangBank(param.getMessage(), param.getSignature());
        logger.debug("银行卡绑定返回:"+ JSON.toJSONString(response));
        if (PlatformPayUtil.isSuccess(response)){
            return payBankDao.save(newPayBak);
        }else {
            throw new RuntimeException(response.getHead().getMessage());
        }
    }

    @Override
    public List<PayBankVo> findByUserCode(String userCode) {
        List<PayBank> list = payBankDao.findByUserCodeAndDeleted(userCode,false);
        return PayBankVo.domainToVoList(list);
    }

    @Override
    public void delBangBank(String txCode,Integer payAccountId) {
        PayBank payBank = payBankDao.findByPayAccountIdAndTxCode(payAccountId,txCode );
        if (payBank == null) throw new MessageRuntimeException("error.payserver.payServer.bank.isnull");
        Param param = payAccountServiceHelper.crateUnBangBankParam(payBank);
        Response response = payClient.delBank(param.getMessage(),param.getSignature());
        logger.debug("解银行卡绑定返回:"+ JSON.toJSONString(response));
        if (PlatformPayUtil.isSuccess(response)){
            payBank.setDeleted(true);
            payBankDao.save(payBank);
        }else {
            throw new RuntimeException(response.getHead().getMessage());
        }
    }
}
