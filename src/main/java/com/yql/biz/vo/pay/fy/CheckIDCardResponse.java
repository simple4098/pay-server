package com.yql.biz.vo.pay.fy;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友验证身份证返回对象 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
@XmlRootElement(name = "RESPONSE")
public class CheckIDCardResponse extends DjPay {
    //版本号
    private String version;
    //响应码
    private String responseCode;
    //中文描述
    private String responseMsg;
    //商户流水号
    private String mchntorderId = PayUtil.randomCode(16);
    //身份证图片
    private String userId;
    //MD5摘要数据
    private String sign;
    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    @XmlElement(name = "RESPONSEMSG")
    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
    @XmlElement(name = "MCHNTORDERID")
    public String getMchntorderId() {
        return mchntorderId;
    }

    public void setMchntorderId(String mchntorderId) {
        this.mchntorderId = mchntorderId;
    }
    @XmlElement(name = "USERID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @XmlElement(name = "SIGN")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
