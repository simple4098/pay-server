package com.yql.biz.vo.pay.response;

import com.yql.biz.vo.pay.request.DjPay;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p> 银行卡解绑绑定关系 </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
public class UninstallBangResponseBody extends BaseBody{
    //机构id
    private String institutionID;
    //解绑流水号
    private String txSNUnBinding;
    //原绑定流水号
    private String txSNBinding;
    @XmlElement(name = "TxSNUnBinding")
    public String getTxSNUnBinding() {
        return txSNUnBinding;
    }

    public void setTxSNUnBinding(String txSNUnBinding) {
        this.txSNUnBinding = txSNUnBinding;
    }

    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "InstitutionID")
    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }
}
