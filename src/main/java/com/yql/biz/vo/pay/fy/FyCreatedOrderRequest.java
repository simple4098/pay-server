package com.yql.biz.vo.pay.fy;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友下单的请求对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "FM")
public class FyCreatedOrderRequest extends DjPay {
    //商户代码
    private String mchntCd;
    //订单金额 ‘分’为单位
    private String amt;
    //保留字段1
    private String rmk1;
    //保留字段2
    private String rmk2;
    //保留字段3
    private String rmk3;
    //MD5摘要数据
    private String sign;
    private String key;

    public FyCreatedOrderRequest() {
    }

    public FyCreatedOrderRequest(String mchntCd, String amt, String key) {
        this.mchntCd = mchntCd;
        this.amt = amt;
        this.key = key;
    }

    @XmlElement(name = "MchntCd")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    @XmlElement(name = "Amt")
    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    @XmlElement(name = "Rmk1")
    public String getRmk1() {
        return rmk1;
    }

    public void setRmk1(String rmk1) {
        this.rmk1 = rmk1;
    }

    @XmlElement(name = "Rmk2")
    public String getRmk2() {
        return rmk2;
    }

    public void setRmk2(String rmk2) {
        this.rmk2 = rmk2;
    }

    @XmlElement(name = "Rmk3")
    public String getRmk3() {
        return rmk3;
    }

    public void setRmk3(String rmk3) {
        this.rmk3 = rmk3;
    }

    @XmlElement(name = "Sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void toMd5() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mchntCd).append("|").append(this.amt).append("|").append(this.key);
        String md5Encode = PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8");
        setSign(md5Encode);
    }
}
