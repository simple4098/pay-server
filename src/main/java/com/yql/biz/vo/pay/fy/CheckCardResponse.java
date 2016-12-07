package com.yql.biz.vo.pay.fy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友验证银行卡响应对象 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
@XmlRootElement(name = "FM")
public class CheckCardResponse extends FyPay {

    private String rcd;
    private String rdesc;
    private String ossn;
    private String cardNo;
    private String mchntCd;
    private String cnm;
    private String insCd;

    @XmlElement(name = "Rcd")
    public String getRcd() {
        return rcd;
    }

    public void setRcd(String rcd) {
        this.rcd = rcd;
    }
    @XmlElement(name = "RDesc")
    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }
    @XmlElement(name = "OSsn")
    public String getOssn() {
        return ossn;
    }

    public void setOssn(String ossn) {
        this.ossn = ossn;
    }
    @XmlElement(name = "CardNo")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    @XmlElement(name = "MchntCd")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }
    @XmlElement(name = "Cnm")
    public String getCnm() {
        return cnm;
    }

    public void setCnm(String cnm) {
        this.cnm = cnm;
    }
    @XmlElement(name = "InsCd")
    public String getInsCd() {
        return insCd;
    }

    public void setInsCd(String insCd) {
        this.insCd = insCd;
    }
}
