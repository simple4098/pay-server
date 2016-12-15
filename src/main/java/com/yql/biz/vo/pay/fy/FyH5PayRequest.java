package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p> 富友h5支付请求对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */

public class FyH5PayRequest extends DjPay {
    //商户id
    private String enctp = "0";
    //请求类型
    private String version = FyVersion.H5_PAY.getValue();
    //请求xml
    private String mchntCd;
    //校验值
    private String fm;


    public FyH5PayRequest(String mchntCd, String fm) {
        this.mchntCd = mchntCd;
        this.fm = fm;
    }

    @XmlElement(name = "ENCTP")
    public String getEnctp() {
        return enctp;
    }

    public void setEnctp(String enctp) {
        this.enctp = enctp;
    }

    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "MCHNTCD")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    @XmlElement(name = "FM")
    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }


}
