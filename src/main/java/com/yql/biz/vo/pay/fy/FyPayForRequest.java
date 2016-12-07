package com.yql.biz.vo.pay.fy;

import com.yql.biz.enums.fy.FyVersion;
import com.yql.biz.util.PayUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * <p> 富友代付的请求对象 </p>
 * @auther simple
 * data 2016/12/7 0007.
 */
@XmlRootElement(name = "payforreq")
public class FyPayForRequest {
    //版本号
    private String ver = FyVersion.VERSION_100.getValue();
    //请求日期
    private String merdt = DateFormatUtils.format(new Date(),"yyyyMMdd");
    //请求流水
    private String orderNo = PayUtil.randomCodeNum(30);
    //总行代码
    private String bankNo;
    //城市code
    private String cityNo;
    //支行名称
    private String branchnm;
    //账h号
    private String accntNo;
    //账户名称
    private String accntNm;
    //金额（分）
    private Integer amt;
    //企业流水号
    private String entseq;
    //备注
    private String memo;
    //手机号
    private String mobile;


    public FyPayForRequest() {
    }

    public FyPayForRequest( String bankNo, String cityNo, String accntNo, String accntNm, Integer amt, String mobile) {
        this.bankNo = bankNo;
        this.cityNo = cityNo;
        this.accntNo = accntNo;
        this.accntNm = accntNm;
        this.amt = amt;
        this.mobile = mobile;
    }

    @XmlElement(name = "ver")
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
    @XmlElement(name = "merdt")
    public String getMerdt() {
        return merdt;
    }

    public void setMerdt(String merdt) {
        this.merdt = merdt;
    }
    @XmlElement(name = "orderno")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    @XmlElement(name = "bankno")
    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    @XmlElement(name = "cityno")
    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }
    @XmlElement(name = "branchnm")
    public String getBranchnm() {
        return branchnm;
    }

    public void setBranchnm(String branchnm) {
        this.branchnm = branchnm;
    }
    @XmlElement(name = "accntno")
    public String getAccntNo() {
        return accntNo;
    }

    public void setAccntNo(String accntNo) {
        this.accntNo = accntNo;
    }
    @XmlElement(name = "accntnm")
    public String getAccntNm() {
        return accntNm;
    }

    public void setAccntNm(String accntNm) {
        this.accntNm = accntNm;
    }
    @XmlElement(name = "amt")
    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }
    public String getEntseq() {
        return entseq;
    }

    public void setEntseq(String entseq) {
        this.entseq = entseq;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    @XmlElement(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
