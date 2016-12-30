package com.yql.biz.vo.pay.ali;

import com.yql.biz.enums.pay.AliWapVersion;
import com.yql.biz.util.PayDateUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p> 支付宝支付 </p>
 * @auther simple
 * data 2016/12/16 0016.
 */
public class AliPayBaseRequest extends DjPay {
    //商户appid
    private String appId;
    //接口名称
    private String method;
    //编码
    private String charset="utf-8";
    //商户生成签名字符串所使用的签名算法类型，目前支持RSA
    private String signType="RSA";
    //请求参数的签名传
    private String sign;
    //发起请求时间戳
    private String timestamp= PayDateUtil.getStringFormatTime(PayDateUtil.FORMAT_TIME);
    private String version= AliWapVersion.ALI_VERSION.getValue();
    //支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
    private String notifyUrl;
    //业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
    private String bizContent;

    public AliPayBaseRequest() {
    }

    public AliPayBaseRequest(String appId, String method, String notifyUrl) {
        this.appId = appId;
        this.method = method;
        this.notifyUrl = notifyUrl;
    }
    @XmlElement(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @XmlElement(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    @XmlElement(name = "charset")
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
    @XmlElement(name = "sign_type")
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
    @XmlElement(name = "sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    @XmlElement(name = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @XmlElement(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @XmlElement(name = "notify_url")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @XmlElement(name = "biz_content")
    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }
}
