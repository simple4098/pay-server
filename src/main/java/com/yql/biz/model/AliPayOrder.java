package com.yql.biz.model;

import com.yql.biz.enums.pay.WxPayType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.yql.core.model.Domain;

/**
 * <p> 阿里订单对象 </p>
 * @auther simple
 * data 2016/11/25 0025.
 */

public class AliPayOrder extends Domain {
    //应用ID
    private String appId;
    //商户号
    private String mchId;
    //随机字符串
    private String nonceStr;
    //签名
    private String sign;
    //商品描述
    private String body;
    //商户订单号
    private String outTradeNo;
    //总额
    private int totalFee;
    //终端ip
    private String spbillCreateIp;
    //异步通知地址
    private String notifyUrl;
    //交易类型
    @Enumerated(value = EnumType.STRING)
    private WxPayType tradeType = WxPayType.APP;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public WxPayType getTradeType() {
        return tradeType;
    }

    public void setTradeType(WxPayType tradeType) {
        this.tradeType = tradeType;
    }
}
