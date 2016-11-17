package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 大金支付平台头部类 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
@XmlRootElement(name = "Head")
public class Head extends DjPay {
    // 机构编号
    private String InstitutionID;
    // 交易编码
    private String TxCode;
    @XmlElement(name = "InstitutionID")
    public String getInstitutionID() {
        return InstitutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.InstitutionID = institutionID;
    }
    @XmlElement(name = "TxCode")
    public String getTxCode() {
        return TxCode;
    }

    public void setTxCode(String txCode) {
        this.TxCode = txCode;
    }
}
