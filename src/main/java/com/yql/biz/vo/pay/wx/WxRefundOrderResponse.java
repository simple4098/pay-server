package com.yql.biz.vo.pay.wx;

import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信订单退款 返回对象 </p>
 * @auther simple
 * data 2016/12/23 0023.
 */
@XmlRootElement(name = "xml")
public class WxRefundOrderResponse extends DjPay {
    //返回信息
    private String returnMsg;
    //返回状态码
    private String returnCode;

    //业务结果
    private String resultCode;
    //错误代码
    private String errCode;
    //错误代码描述
    private String errCodeDes;
    //应用ID
    private String appId;
    //商户号
    private String mchId;
    //设备号
    private String deviceInfo;
    //随机字符串 生成不长于32位随机数
    private String nonceStr;
    //签名
    private String sign;
    //微信支付订单号
    private String transactionId;
    //商户订单号
    private String outTradeNo;
    //商户退款单号
    private String outRefundNo;
    //微信退款单号
    private String refundId;
    //退款渠道
    private String refundChannel;
    //退款金额
    private String refundFee;
    //总额
    private String totalFee;
    //货币种类
    private String feeType;
    //现金支付金额
    private Integer cashFee;
    //现金退款金额
    private Integer cashRefundFee;
    //代金券或立减优惠退款金额
    private Integer couponRefundFee;
    //代金券或立减优惠使用数量
    private Integer couponRefundCount;
    private String couponRefundId;
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

    @XmlElement(name = "out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @XmlElement(name = "total_fee")
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    @XmlElement(name = "device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @XmlElement(name = "result_code")
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @XmlElement(name = "err_code")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @XmlElement(name = "err_code_des")
    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    @XmlElement(name = "fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @XmlElement(name = "cash_fee")
    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    @XmlElement(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @XmlElement(name = "return_msg")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @XmlElement(name = "return_code")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    @XmlElement(name = "out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    @XmlElement(name = "refund_id")
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    @XmlElement(name = "refund_channel")
    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }
    @XmlElement(name = "refund_fee")
    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }
    @XmlElement(name = "cash_refund_fee")
    public Integer getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(Integer cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }
    @XmlElement(name = "coupon_refund_fee")
    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }
    @XmlElement(name = "coupon_refund_count")
    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }
    @XmlElement(name = "coupon_refund_id")
    public String getCouponRefundId() {
        return couponRefundId;
    }

    public void setCouponRefundId(String couponRefundId) {
        this.couponRefundId = couponRefundId;
    }
}
