package com.yql.biz.vo.pay.fy;

import com.yql.biz.util.PayUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友验证银行卡请求对象 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
@XmlRootElement(name = "FM")
public class CheckCardRequest extends FyPay {
    //商户id
    private String mchntCd;
    //商户流水号
    private String oSsn = PayUtil.randomCode(30);
    //银行卡号
    private String ono;
    //姓名
    private String onm;
    //卡证件类型 0.身份证 1.护照 2.军官证 3.士
    //兵证 4.回乡证 6.户口本 7.其它
    private String oCerTp;
    //证件号
    private String ocerNo;
    //银行预留手 机号
    private String mno;

    public CheckCardRequest() {
    }

    public CheckCardRequest(String mchntCd, String ono, String onm, String oCerTp, String ocerNo) {
        this.mchntCd = mchntCd;
        this.ono = ono;
        this.onm = onm;
        this.oCerTp = oCerTp;
        this.ocerNo = ocerNo;
    }

    @XmlElement(name = "MchntCd")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    @XmlElement(name = "OSsn")
    public String getoSsn() {
        return oSsn;
    }

    public void setoSsn(String oSsn) {
        this.oSsn = oSsn;
    }
    @XmlElement(name = "Ono")
    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }
    @XmlElement(name = "Onm")
    public String getOnm() {
        return onm;
    }

    public void setOnm(String onm) {
        this.onm = onm;
    }
    @XmlElement(name = "OCerTp")
    public String getoCerTp() {
        return oCerTp;
    }

    public void setoCerTp(String oCerTp) {
        this.oCerTp = oCerTp;
    }

    @XmlElement(name ="OCerNo" )
    public String getOcerNo() {
        return ocerNo;
    }

    public void setOcerNo(String ocerNo) {
        this.ocerNo = ocerNo;
    }
    @XmlElement(name = "Mno")
    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }


    public String toMd5String(String key){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mchntCd).append("|").append(getVer()).append("|").append(this.oSsn).append("|");
        stringBuffer.append(this.ono).append("|").append(this.oCerTp).append("|").append(this.ocerNo);
        stringBuffer.append("|").append(key);
        return  PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8");
    }

}
