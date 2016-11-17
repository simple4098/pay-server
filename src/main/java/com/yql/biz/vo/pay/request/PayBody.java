package com.yql.biz.vo.pay.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 快捷支付请求body </p>
 * @auther simple
 * data 2016/11/17 0017.
 */
@XmlRootElement(name = "Body")
public class PayBody extends DjPay {
    //支付交易流水号
    private String paymentNo;
    //绑定流水号
    private String txSNBinding;
    //支付金额 （分）
    private Integer amount;
    //结算标识
    private String settlementFlag;
    //备注
    private String remark;

    @XmlElement(name = "PaymentNo")
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
    @XmlElement(name = "TxSNBinding")
    public String getTxSNBinding() {
        return txSNBinding;
    }

    public void setTxSNBinding(String txSNBinding) {
        this.txSNBinding = txSNBinding;
    }
    @XmlElement(name = "Amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @XmlElement(name = "SettlementFlag")
    public String getSettlementFlag() {
        return settlementFlag;
    }

    public void setSettlementFlag(String settlementFlag) {
        this.settlementFlag = settlementFlag;
    }
    @XmlElement(name = "Remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
