package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyRequestType;
import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * <p> 富友支付请求对象 </p>
 * @auther simple
 * data 2016/12/7 0007.
 */

public class FyPayRequest extends DjPay{
    //商户id
    private String merid;
    //请求类型
    private String reqType;
    //请求xml
    private String xml;
    //校验值
    private String mac;

    public FyPayRequest(String merid, FyRequestType reqType, String xml) {
        this.merid = merid;
        this.reqType = reqType.name();
        this.xml = xml;
    }

    @XmlElement(name = "merid")
    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }
    @XmlElement(name = "reqtype")
    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
    @XmlElement(name = "xml")
    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    @XmlElement(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String toMd5String(String key){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.merid).append("|").append(key).append("|").append(this.reqType).append("|").append(this.xml);
        String md5Encode = PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8").toUpperCase();
        return  md5Encode;
    }

}
