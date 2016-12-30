package com.yql.biz.vo.pay.ali;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p> 支付宝查询订单 </p>
 * @auther simple
 * data 2016/12/16 0016.
 */
public class AliQueryOrderRequest {

    //订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
    private String outTradeNo;
    //支付宝交易号，和商户订单号不能同时为空
    private String tradeNo;

    public AliQueryOrderRequest(String outTradeNo, String tradeNo) {
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
    }

    public AliQueryOrderRequest() {
    }

    @JSONField(name = "out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    @JSONField(name = "trade_no")
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
