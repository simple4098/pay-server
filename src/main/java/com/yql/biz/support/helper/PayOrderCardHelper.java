package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.PayClient;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.model.PayBank;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.response.Response;
import com.yql.framework.mq.MessagePublisher;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>银行卡支付</p>
 * creator simple
 * data 2016/11/11 0011.
 */
@Component
public class PayOrderCardHelper implements IPayOrderAccountHelper {
    private static final Logger loger = LoggerFactory.getLogger(PayOrderCardHelper.class);
    @Resource
    private PayClient payClient;
    @Resource
    private IPayOrderCardParamHelper payOrderCardParamHelper;
    @Resource
    private MessagePublisher messagePublisher;
    @Resource
    private IPayBankDao payBankDao;

    @Override
    public PayOrderVo orderType(PayOrderVo payOrderVo) {
        PayBank payBank = payBankDao.findByUserCodeAndTxCode(payOrderVo.getUserCode(),payOrderVo.getTxCode());
        Param payParam = payOrderCardParamHelper.getPayParam(payOrderVo,payBank);
        Response pay = payClient.pay(payParam.getMessage(), payParam.getSignature());
        boolean payStatus = false;
        TextMessage textMessage = null;
        if (PlatformPayUtil.isSuccess(pay)){
            payStatus = true;
            textMessage = new TextMessage("TEST_USER_REGISTER", "PAY-SERVER-TAG", "pay-key-success", "银行卡支付成功");
        }else {
            payOrderVo.setErrorMsg(pay.getHead().getMessage());
            textMessage = new TextMessage("TEST_USER_REGISTER", "PAY-SERVER-TAG", "pay-key-fail", "银行卡支付失败");
        }
        payOrderVo.setPayStatus(payStatus);
        messagePublisher.send(textMessage);
        loger.debug("银行卡快捷支付返回"+ JSON.toJSONString(pay));
        return payOrderVo;
    }
}
