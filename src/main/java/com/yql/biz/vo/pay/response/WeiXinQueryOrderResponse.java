package com.yql.biz.vo.pay.response;

import com.yql.biz.enums.pay.WxTradeState;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.wx.WeiXinNotifyVo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信支付【查询订单】微信返回的对象 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "xml")
public class WeiXinQueryOrderResponse extends DjPay {

    /**
     *  SUCCESS—支付成功
     *  REFUND—转入退款
     *  NOTPAY—未支付
     *  CLOSED—已关闭
     *  REVOKED—已撤销（刷卡支付）
     *  USERPAYING--用户支付中
     *  PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private WxTradeState tradeState;
    //交易状态描述
    private String tradeStateDesc;

    //应用ID
    private String appId;
    //商户号
    private String mchId;
    //设备号
    private String deviceInfo;
    //业务结果
    private String resultCode;
    //返回信息
    private String returnMsg;
    //返回状态码
    private String returnCode;
    //错误代码
    private String errCode;
    //错误代码描述
    private String errCodeDes;
    //用户在商户appid下的唯一标识
    private String openid;
    //付款银行
    private String bankType;
    //货币种类
    private String feeType;
    //现金支付金额
    private Integer cashFee;
    //现金支付货币类型
    private Integer cashFeeType;
    //代金券或立减优惠金额
    private Integer couponFee;
    //代金券或立减优惠使用数量
    private Integer couponCount;
    //微信支付订单号
    private String transactionId;
    //商户数据包
    private String attach;
    //支付完成时间
    private String timeEnd;
    //随机字符串 生成不长于32位随机数
    private String nonceStr;
    //签名
    private String sign;
    //商品描述
    private String body;
    //商户订单号
    private String outTradeNo;
    //总额
    private String totalFee;
    //终端ip
    private String spbillCreateIp;
    //异步通知地址
    private String notifyUrl;
    //交易类型
    @Enumerated(value = EnumType.STRING)
    private String tradeType;
    //是否关注
    private String isSubscribe;

    @XmlElement(name = "is_subscribe")
    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

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
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    @XmlElement(name = "spbill_create_ip")
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    @XmlElement(name = "notify_url")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @XmlElement(name = "trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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

    @XmlElement(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @XmlElement(name = "bank_type")
    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
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

    @XmlElement(name = "cash_fee_type")
    public Integer getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(Integer cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    @XmlElement(name = "coupon_fee")
    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    @XmlElement(name = "coupon_count")
    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    @XmlElement(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @XmlElement(name = "attach")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @XmlElement(name = "time_end")
   /* @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
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
    @XmlElement(name = "trade_state")
    public WxTradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(WxTradeState tradeState) {
        this.tradeState = tradeState;
    }

    @XmlElement(name = "trade_state_desc")
    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
