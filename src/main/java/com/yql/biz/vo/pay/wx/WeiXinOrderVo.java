package com.yql.biz.vo.pay.wx;

import com.yql.biz.enums.pay.WxPayType;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 封装微信统一订单对象 </p>
 * @auther simple
 * data 2016/11/28 0025.
 */
@XmlRootElement(name = "xml")
public class WeiXinOrderVo extends DjPay {
    //应用ID
    private String appId ;
    //商户号
    private String mchId ;
    //随机字符串 生成不长于32位随机数
    private String nonceStr = PayUtil.randomCode(16);
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
    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
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
    public WxPayType getTradeType() {
        return tradeType;
    }

    public void setTradeType(WxPayType tradeType) {
        this.tradeType = tradeType;
    }
}
