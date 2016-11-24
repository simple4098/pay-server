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
    private String orderNo;
    //支付号（系统生产）
    private Long payNo;
    //第三方支付返回的订单号
    private String payOrder;
    //对方userCode
    private String otherUserCode;
    //支付类型
    private PayType payType;
    //持卡人
    private String cardholder;
    //支付总金额
    private BigDecimal totalPrice;
    //支付是否成功
    private Integer payStatus;
    //支付信息
    private String msg;
    private Integer payBankId;

    public Integer getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(Integer payBankId) {
        this.payBankId = payBankId;
    }

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

    public Long getPayNo() {
        return payNo;
    }

    public void setPayNo(Long payNo) {
        this.payNo = payNo;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

}
