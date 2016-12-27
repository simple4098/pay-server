package com.yql.biz.support.pay;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;

/**
 * <p>关闭第三方支付订单信息</p>
 * creator simple
 * data 2016/11/11 0011.
 */
public interface ICloseOrder {

    WeiXinCloseOrderResponse closeOrder(PayOrderAccount payOrderAccount);

    boolean supports(PayOrderAccount payOrderAccount);
}
