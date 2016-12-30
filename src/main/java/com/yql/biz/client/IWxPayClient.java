package com.yql.biz.client;

import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinQueryOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.wx.WxRefundOrderResponse;
import com.yql.core.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 * @author simple
 */
/*@FeignClient(value = "order-center",fallback = WxPayClient.class)*/
public interface IWxPayClient {

    /**
     * 微信服务端 统一下单
     * @param xml 调用微信接口的xml数据
     * @return
     */
   ResponseModel<WeiXinResponse> sendPrepay(@RequestParam String xml);

    /**
     * 查询微信订单信息
     * @param payNo 微信支付-商户支付订单号
     */
    ResponseModel<WeiXinQueryOrderResponse> queryWxOrder(String payNo);

    /**
     * 关闭微信订单
     * @param xml 请求xml
     */
    ResponseModel<WeiXinCloseOrderResponse>  closeOrder(@RequestParam String xml);

    /**
     * 微信退款
     * @param payOrderAccount 支付订单信息
     */
    WxRefundOrderResponse refundOrder(PayOrderAccount payOrderAccount);


}
