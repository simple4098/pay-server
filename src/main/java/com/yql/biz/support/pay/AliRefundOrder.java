package com.yql.biz.support.pay;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.yql.biz.client.IAliPayClient;
import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayOrderAccount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 订单退款 </p>
 * @auther simple
 * data 2016/12/23 0023.
 */
@Component
public class AliRefundOrder implements IRefundOrder {
    @Resource
    private IAliPayClient aliPayClient;

    @Override
    public boolean refundOrder(PayOrderAccount payOrderAccount) {
        AlipayTradeFastpayRefundQueryResponse refundOrder = aliPayClient.refundOrder(payOrderAccount);
        return refundOrder.isSuccess();
    }

    @Override
    public boolean supports(PayOrderAccount payOrderAccount) {
        return PayType.ALI_PAY.equals(payOrderAccount.getPayType());
    }
}
