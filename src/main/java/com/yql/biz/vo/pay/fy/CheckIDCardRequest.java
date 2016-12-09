package com.yql.biz.vo.pay.fy;

import com.yql.biz.util.PayUtil;
import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 富友验证身份证请求对象 </p>
 * @auther simple
 * data 2016/12/6 0006.
 */
@XmlRootElement(name = "ORDER")
public class CheckIDCardRequest extends DjPay {
    //版本号
    private String version;
    //业务号 NN:不返回照片  YY：要返回照片
    private String typeId = "NN";
    //商户id
    private String mchntCd;
    //商户流水号
    private String mchntorderId = PayUtil.randomCode(16);
    //姓名
    private String name;
    //用户身份证号
    private String idNo;
    //秘钥
    private String sign;



    @XmlElement(name = "MCHNTCD")
    public String getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }
    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @XmlElement(name = "TYPEID")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    @XmlElement(name = "MCHNTORDERID")
    public String getMchntorderId() {
        return mchntorderId;
    }

    public void setMchntorderId(String mchntorderId) {
        this.mchntorderId = mchntorderId;
    }
    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "IDNO")
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    @XmlElement(name = "SIGN")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String toMd5String(String key){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.version).append("|").append(this.typeId).append("|").append(this.mchntCd).append("|");
        stringBuffer.append(this.mchntorderId).append("|").append(this.name).append("|").append(this.idNo);
        stringBuffer.append("|").append(key);
        return  PayUtil.MD5Encode(stringBuffer.toString(), "UTF-8");
    }

}
