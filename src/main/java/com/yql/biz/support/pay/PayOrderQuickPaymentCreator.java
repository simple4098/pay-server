package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.PayClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.SendMsgTag;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayOrderVo;
import com.yql.biz.vo.ResultPayOrder;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.response.PayMessageValidateResponse;
import com.yql.biz.vo.pay.response.PayMessageValidateResponseBody;
import com.yql.framework.mq.model.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>银行卡支付</p>
 * creator simple
 * data 2016/11/11 0011.
 */
@Component
public class PayOrderQuickPaymentCreator implements IPayOrderCreator {
    private static final Logger log = LoggerFactory.getLogger(PayOrderQuickPaymentCreator.class);
    @Resource
    private PayClient payClient;
    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
    @Resource
    private SendMessageHelper sendMessageHelper;
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;
    @Resource
    private IPayAccountDao payAccountDao;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        //支付验证支付密码
        PayAccount payAccount = payAccountDao.findByUserCode(payOrderVo.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payOrderVo.getPayPassword(),payAccount);
        if (StringUtils.isEmpty(payOrderVo.getTxCode())) throw new MessageRuntimeException("com.yql.validation.constraints.txCode.notnull");
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
            sendMessageHelper.sendMessage(textMessage);
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
