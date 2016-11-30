package com.yql.biz.vo.pay.wx;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.Locale;

/**
 * <p> 给移动端返回参数对象</p>
 * @auther simple
 * data 2016/11/29 0029.
 */
@XmlRootElement(name = "xml")
public class WeiXinAppRequest  extends DjPay {
    //应用ID
    private String appId;
    //商户号
    private String partnerid;
    //预付订单号
    private String prepayid;
    private String packageStr = "Sign=WXPay" ;
    //时间戳
    private String timestamp = PayUtil.getTimeStamp();
    private String nonceStr;
    private String sign;


    @XmlElement(name = "appid")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @XmlElement(name = "partnerid")
    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    @XmlElement(name = "prepayid")
    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    @XmlElement(name = "package")
    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }
    @XmlElement(name = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @XmlElement(name = "noncestr")
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
}
