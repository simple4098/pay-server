package com.yql.biz.vo;

import com.yql.biz.enums.PayType;

import java.math.BigDecimal;

/**
 * <p> 返给订单系统信息 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public class ResultPayOrder {
    //信息
    private String msg;
    //订单号
    private String orderNo;
    //支付订单号
    private String payNo	;
    //支付状态
    private Integer payStatus;
    private BigDecimal payPrice;
    //支付通道标识 微信openid  快捷支付银行卡编码
    private String txCode;
    private PayType payType;
    //private WeiXinAppRequest appRequest;

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

  /*  public WeiXinAppRequest getAppRequest() {
        return appRequest;
    }

    public void setAppRequest(WeiXinAppRequest appRequest) {
        this.appRequest = appRequest;
    }*/

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }
}
