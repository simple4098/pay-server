package com.yql.biz.vo;

import com.yql.biz.enums.pay.WxTradeState;

import java.math.BigDecimal;

/**
 * <p> 查询微信订单信息，返回数据对象 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public class ResultWxQueryOrder {
    //信息
    private String msg;
    //订单号
    private String orderNo;
    //支付订单号
    private String payNo;
    //第三方支付号
    private String payOrder;
    //支付状态
    private Integer payStatus;
    private BigDecimal payPrice;
    //支付通道标识 微信openid  快捷支付银行卡编码
    private String txCode;

    private WxTradeState wxTradeState;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public WxTradeState getWxTradeState() {
        return wxTradeState;
    }

    public void setWxTradeState(WxTradeState wxTradeState) {
        this.wxTradeState = wxTradeState;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }
}
