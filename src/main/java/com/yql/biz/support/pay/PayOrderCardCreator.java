package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.PayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.helper.IPayOrderCardParamHelper;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.response.PayMessageValidateResponse;
import com.yql.biz.vo.pay.response.PayMessageValidateResponseBody;
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
public class PayOrderCardCreator implements IPayOrderCreator {
    private static final Logger log = LoggerFactory.getLogger(PayOrderCardCreator.class);
    @Resource
    private PayClient payClient;
    @Resource
    private IPayOrderCardParamHelper payOrderCardParamHelper;
    @Resource
    private MessagePublisher messagePublisher;
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private ApplicationConf applicationConf;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        PayBank payBank = payBankDao.findByUserCodeAndTxCode(payOrderVo.getUserCode(),payOrderVo.getTxCode());
        Param payParam = payOrderCardParamHelper.getPayParam(payOrderVo,payBank);
        PayMessageValidateResponse pay = payClient.pay(payParam.getMessage(), payParam.getSignature());
        log.debug("银行卡快捷支付返回:"+ JSON.toJSONString(pay));
        PayMessageValidateResponseBody responseBody = pay.getPayMessageValidateResponseBody();
        if (PlatformPayUtil.isSuccess(pay)){
            ResultPayOrder resultPayOrder = PayOrderVo.toSendResultOrder(responseBody,payOrderVo);
            payOrderVo.setPayOrder(responseBody.getPaymentNo());
            payOrderVo.setMsg(responseBody.getResponseMessage());
            payOrderVo.setPayStatus(responseBody.getStatus());
            payOrderVo.setBankTxTime(responseBody.getBankTxTime());
            payOrderVo.setPayBankId(payBank.getId());
            TextMessage textMessage = new TextMessage(applicationConf.getSendMsgTopic(),
                    SendMsgTag.PAY_SERVER_STATUS.name(),
                    payOrderVo.getOrderNo(), JSON.toJSONString(resultPayOrder));
            messagePublisher.send(textMessage);
            log.debug("发送消息："+JSON.toJSONString(textMessage));
        }else {
            log.info("支付失败 订单号【"+payOrderVo.getOrderNo()+"】");
            throw new RuntimeException(pay.getHead().getMessage()+" 支付失败 订单号【"+payOrderVo.getOrderNo()+"】");
        }
        return payOrderVo;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.QUICK_PAYMENT.equals(payOrderVo.getPayType());
    }
}
