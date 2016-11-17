package com.yql.biz.vo.pay.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 查询绑定关系 response body </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Body")
public class QueryBangResponseBody extends BaseBody {
    //机构号码
    private String institutionID;
    //绑定流水号
    private String txSNBinding;
    /**
     * 支付卡类型（银联返回，不是 2501-Request
     * 中的 CardType）
     *  00=未知
     *  01=借记账户
     *  02=贷记账户
     *  03=准贷记账户
     *  04=借贷合一账户
     *  05=预付费账户
     *  06=半开放预付费账户
     */
    private String payCardType;

    @XmlElement(name = "InstitutionID")
    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(String institutionID) {
        this.institutionID = institutionID;
    }
    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "PayCardType")
    public String getPayCardType() {
        return payCardType;
    }

    public void setPayCardType(String payCardType) {
        this.payCardType = payCardType;
    }
}
