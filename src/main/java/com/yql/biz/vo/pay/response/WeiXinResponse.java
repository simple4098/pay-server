package com.yql.biz.vo.pay.response;

import com.yql.biz.enums.pay.WxPayResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 微信支付【统一下单】 微信返回的内容。 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "xml")
public class WeiXinResponse {
    private WxPayResult returnCode;
    private String returnMsg;
    private String appId;
    private String mchId;
    private String nonceStr;
    private String sign;
    private WxPayResult resultCode;
    private String errCode;
    private String errCodeDes;
    private String tradeType;
    private String prepayId;

    @XmlElement(name = "return_code")
    public WxPayResult getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(WxPayResult returnCode) {
        this.returnCode = returnCode;
    }
    @XmlElement(name = "return_msg")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
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
    @XmlElement(name = "result_code")
    public WxPayResult getResultCode() {
        return resultCode;
    }

    public void setResultCode(WxPayResult resultCode) {
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
    @XmlElement(name = "trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    @XmlElement(name = "prepay_id")
    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
