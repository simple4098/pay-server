package com.yql.biz.model;

import com.yql.biz.enums.PayType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>支付订单日志记录表</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "pay_order_detail")
public class PayOrderAccountDetail extends Domain {
    private Integer payAccountId;
    //支付订单id
    private Integer payOrderAccountId;
    //支付订单号
    private Long orderNo;
    //支付号（系统生产）
    private Long payNo;
    //第三方支付返回的订单号
    private Long payOrder;
    //对方userCode
    private String otherUserCode;
    //支付类型
    private PayType payType;
    //持卡人
    private String cardholder;
    //银行卡号
    private String bankCard;
    //支付总金额
    private BigDecimal totalPrice;
    //支付是否成功
    private boolean payStatus;
    //支付错误信息
    private String errorMsg;



    public Integer getPayOrderAccountId() {
        return payOrderAccountId;
    }

    public void setPayOrderAccountId(Integer payOrderAccountId) {
        this.payOrderAccountId = payOrderAccountId;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
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

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
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


}
