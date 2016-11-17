package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 绑定银行卡 验证请求 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Body")
public class BangMessageValidateBody extends DjPay {
    //绑定流水号
    private String txSNBinding;
    //验证码
    private String sMSValidationCode;

    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "SMSValidationCode")
    public String getsMSValidationCode() {
        return sMSValidationCode;
    }

    public void setsMSValidationCode(String sMSValidationCode) {
        this.sMSValidationCode = sMSValidationCode;
    }
}
