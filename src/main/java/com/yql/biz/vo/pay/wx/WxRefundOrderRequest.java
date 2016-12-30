package com.yql.biz.vo.pay.wx;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信订单退款对象 </p>
 * @auther simple
 * data 2016/12/23 0023.
 */
@XmlRootElement(name = "xml")
public class WxRefundOrderRequest extends DjPay {

    //应用ID
    private String appId ;
    //商户号
    private String mchId ;
    //随机字符串 生成不长于32位随机数
    private String nonceStr = PayUtil.randomCode(32);
    //签名
    private String sign;
    //商品描述
    private String body;
    //商户订单号
    private String outTradeNo;
    //总额（分）
    private Integer totalFee;
    //款总金额，订单总金额，单位为分，只能为整数，详见支付金额
    private Integer refundFee;
    //微信订单号
    private String transactionId;
    //微信退款订单号
    private String outRefundNo;
    //操作员
    private String opUserId;
    //退款资金来源
    private String refundAccount;

    @XmlElement(name = "appid")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @XmlElement(name = "mch_id")
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    @XmlElement(name = "nonce_str")
    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    @XmlElement(name = "sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @XmlElement(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlElement(name = "out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @XmlElement(name = "total_fee")
    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    @XmlElement(name = "refund_fee")
    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
    @XmlElement(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    @XmlElement(name = "out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    @XmlElement(name = "op_user_id")
    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }
    @XmlElement(name = "refund_account")
    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }
}
