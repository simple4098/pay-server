package com.yql.biz.client;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yql.biz.model.PayOrderAccount;

/**
 * ali pay 支付请求参数
 * @author simple
 */

public interface IAliPayClient {

    /**
     * ali pay wap支付form表单
     * @param payOrderAccount 订单参数
     */
   String aliWapFrom(PayOrderAccount payOrderAccount);

    /**
     * 阿里订单查询
     * @param payOrderVo 订单信息  payNo 支付订单号
     */
    AlipayTradeQueryResponse queryAliOrder(PayOrderAccount payOrderVo);

    /**
     * 阿里订单退款
     * @param payOrderVo 订单信息
     */
    AlipayTradeFastpayRefundQueryResponse refundOrder(PayOrderAccount payOrderVo);
}
