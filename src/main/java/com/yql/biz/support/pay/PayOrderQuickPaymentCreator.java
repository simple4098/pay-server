package com.yql.biz.support.pay;

import com.yql.biz.enums.PayType;
import com.yql.biz.vo.PayOrderVo;
import org.springframework.stereotype.Component;

/**
 * <p>银行卡支付</p>
 * creator simple
 * data 2016/11/11 0011.
 */
@Component
public class PayOrderQuickPaymentCreator implements IPayOrderCreator {

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        throw new RuntimeException("暂未开通快捷支付");
        //支付验证支付密码
        /*PayAccount payAccount = payAccountDao.findByUserCode(payOrderVo.getUserCode());
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
        return payOrderVo;*/
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.QUICK_PAYMENT.equals(payOrderVo.getPayType());
    }
}
