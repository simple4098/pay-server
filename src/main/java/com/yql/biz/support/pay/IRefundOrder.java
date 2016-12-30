package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;

/**
 * <p>第三方支付订单退款</p>
 * creator simple
 * data 2016/11/11 0011.
 */
public interface IRefundOrder {

    boolean refundOrder(PayOrderAccount payOrderAccount);

    boolean supports(PayOrderAccount payOrderAccount);
}
