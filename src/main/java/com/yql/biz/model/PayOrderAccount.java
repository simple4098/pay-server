package com.yql.biz.model;

import com.yql.biz.enums.PayType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>支付记录表</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "pay_order_account")
public class PayOrderAccount extends Domain {
    @Column(name = "pay_account_id")
    private Long payAccountId;
    //支付订单号
    @Column(name = "order_no")
    private Long orderNo;
    //支付号（系统生产）
    @Column(name = "pay_no")
    private Long payNo;
    //第三方支付返回的订单号
    @Column(name = "pay_order")
    private Long payOrder;
    //对方userCode
    @Column(name = "other_user_code")
    private String otherUserCode;
    //支付类型
    @Column(name = "pay_type")
    private PayType payType;
    //银行卡号
    @Column(name = "bank_card")
    private String bankCard;
    //支付总金额
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    //支付错误信息
    @Column(name = "error_msg")
    private String errorMsg;
    //支付错误编码
    @Column(name = "error_code")
    private String errorCode;

    public Long getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Long payAccountId) {
        this.payAccountId = payAccountId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getPayNo() {
        return payNo;
    }

    public void setPayNo(Long payNo) {
        this.payNo = payNo;
    }

    public Long getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(Long payOrder) {
        this.payOrder = payOrder;
    }

    public String getOtherUserCode() {
        return otherUserCode;
    }

    public void setOtherUserCode(String otherUserCode) {
        this.otherUserCode = otherUserCode;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
