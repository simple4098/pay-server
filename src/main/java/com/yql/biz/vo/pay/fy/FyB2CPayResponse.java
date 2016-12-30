package com.yql.biz.vo.pay.fy;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友b2c支付的返回对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "xml")
public class FyB2CPayResponse extends DjPay {
    //商户代码
    private String mchnt_cd;
    //商户订单号
    private String orderId = PayUtil.randomCodeNum(30);
    //支付金额 ‘分’为单位
    private Integer orderAmt;
    //订单状态
    private String orderSt;
    //错误代码 0000 表示成功 其他失败
    private String orderPayCode;
    //中文描述
    private String orderPayError;
    //富友流水号
    private String fySsn;
    //保留字段
    private String resv1;
    //MD5摘要数据
    private String md5;

    @XmlElement(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "order_amt")
    public Integer getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Integer orderAmt) {
        this.orderAmt = orderAmt;
    }

    @XmlElement(name = "md5")
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @XmlElement(name = "mchnt_cd")
    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }

    @XmlElement(name = "order_st")
    public String getOrderSt() {
        return orderSt;
    }

    public void setOrderSt(String orderSt) {
        this.orderSt = orderSt;
    }

    @XmlElement(name = "order_pay_code")
    public String getOrderPayCode() {
        return orderPayCode;
    }

    public void setOrderPayCode(String orderPayCode) {
        this.orderPayCode = orderPayCode;
    }

    @XmlElement(name = "order_pay_error")
    public String getOrderPayError() {
        return orderPayError;
    }

    public void setOrderPayError(String orderPayError) {
        this.orderPayError = orderPayError;
    }

    @XmlElement(name = "fy_ssn")
    public String getFySsn() {
        return fySsn;
    }

    public void setFySsn(String fySsn) {
        this.fySsn = fySsn;
    }

    @XmlElement(name = "resv1")
    public String getResv1() {
        return resv1;
    }

    public void setResv1(String resv1) {
        this.resv1 = resv1;
    }
}
