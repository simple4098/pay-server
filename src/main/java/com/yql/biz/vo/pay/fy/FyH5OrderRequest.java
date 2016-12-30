package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友h5支付请求对象 </p>
 *
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "ORDER")
public class FyH5OrderRequest extends DjPay {

    //交易类型
    private String type = "10";
    private String version = FyVersion.H5_PAY.getValue();
    //是否隐藏支付页面富友的 logo，1 隐藏，0 显示
    private String logotp = "0";
    //商户订单号
    private String mchntorderId = PayUtil.randomCodeNum(20);
    //商户代码
    private String mchntCd;
    //userCode
    private String userid;
    //交易金额 单位（分）
    private String amt;
    //支付银行卡
    private String bankCard;
    //后台通知URL
    private String backurl;
    //支付失败URL
    private String reurl;
    //页面通知URL 在富友的支付成功页面跳转到该地址
    private String homeUrl;
    //用户姓名
    private String name;
    //证件类型
    private String idType = "0";
    //身份证号码
    private String idNo;
    private String signTp = "md5";
    private String sign;
    //商户key
    private String key;

    public FyH5OrderRequest() {
    }

    public FyH5OrderRequest(String mchntCd, String userid, String amt, String bankCard, String backurl, String reurl, String homeUrl, String name, String idNo, String key) {
        this.mchntCd = mchntCd;
        this.userid = userid;
        this.amt = amt;
        this.bankCard = bankCard;
        this.backurl = backurl;
        this.reurl = reurl;
        this.homeUrl = homeUrl;
        this.name = name;
        this.idNo = idNo;
        this.key = key;
    }

    @XmlElement(name = "MCHNTCD")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }

    @XmlElement(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "LOGOTP")
    public String getLogotp() {
        return logotp;
    }

    public void setLogotp(String logotp) {
        this.logotp = logotp;
    }

    @XmlElement(name = "MCHNTORDERID")
    public String getMchntorderId() {
        return mchntorderId;
    }

    public void setMchntorderId(String mchntorderId) {
        this.mchntorderId = mchntorderId;
    }

    @XmlElement(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @XmlElement(name = "AMT")
    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    @XmlElement(name = "BANKCARD")
    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    @XmlElement(name = "BACKURL")
    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    @XmlElement(name = "REURL")
    public String getReurl() {
        return reurl;
    }

    public void setReurl(String reurl) {
        this.reurl = reurl;
    }

    @XmlElement(name = "HOMEURL")
    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "IDTYPE")
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @XmlElement(name = "IDNO")
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @XmlElement(name = "SIGNTP")
    public String getSignTp() {
        return signTp;
    }

    public void setSignTp(String signTp) {
        this.signTp = signTp;
    }

    @XmlElement(name = "SIGN")
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

    public String toMd5() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.type).append("|").append(this.version).append("|").append(this.mchntCd).append("|").append(this.mchntorderId).append("|").append(this.userid);
        stringBuffer.append("|").append(this.amt).append("|").append(this.bankCard).append("|").append(this.backurl).append("|").append(this.name).append("|").append(this.idNo);
        stringBuffer.append("|").append(this.idType).append("|").append(this.logotp).append("|").append(this.homeUrl).append("|").append(this.reurl).append("|").append(key);
        String md5Encode = PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8").toUpperCase();
        return md5Encode;
    }
}
