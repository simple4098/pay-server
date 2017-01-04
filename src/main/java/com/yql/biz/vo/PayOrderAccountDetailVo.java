package com.yql.biz.vo;

import com.alibaba.fastjson.JSON;
import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayOrderAccountDetailVo {
    private String userCode;
    private Long payAccountId;
    private Integer payOrderAccountId;
    //支付订单号
    private String orderNo;
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
    //支付错误信息
    private String errorMsg;
    //支付错误编码
    private String errorCode;
    //支付是否成功
    private boolean payStatus;

    public Integer getPayOrderAccountId() {
        return payOrderAccountId;
    }

    public void setPayOrderAccountId(Integer payOrderAccountId) {
        this.payOrderAccountId = payOrderAccountId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Long getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Long payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public static PayOrderAccountDetail toDomain(PayOrderAccount payOrderAccount) {
        PayOrderAccountDetail payOrderAccountDetail = new PayOrderAccountDetail();
        BeanUtils.copyProperties(payOrderAccount,payOrderAccountDetail);
        payOrderAccountDetail.setUpdatedTime(new Date());
        payOrderAccountDetail.setCreatedTime(new Date());
        return payOrderAccountDetail;
    }
}
