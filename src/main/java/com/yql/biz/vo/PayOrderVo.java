package com.yql.biz.vo;

import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayOrderAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * creator simple
 * data 2016/11/10 0010.
 */
public class PayOrderVo {
    @NotNull(message = "{com.yql.validation.constraints.userCode.message}")
    private String userCode;
    private Integer payAccountId;
    //支付订单号
    @NotNull(message = "{com.yql.validation.constraints.OrderNo.message}")
    private Long orderNo;
    //支付号（系统生产）
    private Long payNo;
    //第三方支付返回的订单号
    private String payOrder;
    //对方userCode
    private String otherUserCode;
    //支付类型
    @NotNull(message = "{com.yql.validation.constraints.PayType.message}")
    private PayType payType;
    //持卡人
    private String cardholder;
    //交易码
    @NotNull(message = "{com.yql.validation.constraints.txCode.notnull}")
    private String txCode;
    //支付总金额
    @NotNull(message = "{com.yql.validation.constraints.totalPrice.message}")
    private BigDecimal totalPrice;
    //支付错误信息
    private String errorMsg;
    //交易状态 10=处理中 20=支付成功 30=支付失败
    private Integer payStatus;
    //应该处理时间
    private Date bankTxTime;
    //备注
    private String remark;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
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

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getBankTxTime() {
        return bankTxTime;
    }

    public void setBankTxTime(Date bankTxTime) {
        this.bankTxTime = bankTxTime;
    }

    public static PayOrderAccount toDomain(PayOrderVo payOrderVo) {
        PayOrderAccount payOrderAccount = new PayOrderAccount();
        BeanUtils.copyProperties(payOrderVo,payOrderAccount);
        return payOrderAccount;
    }

    public static PayOrderVo domainToVo(PayOrderAccount result) {
        PayOrderVo payOrderVo = new PayOrderVo();
        BeanUtils.copyProperties(result,payOrderVo);
        return payOrderVo;
    }

    public static ResultPayOrder toResultOrder(PayOrderAccount result) {
        ResultPayOrder payOrder = new ResultPayOrder();
        BeanUtils.copyProperties(result,payOrder);
        return payOrder;
    }
}
