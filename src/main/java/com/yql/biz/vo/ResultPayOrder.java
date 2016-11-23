package com.yql.biz.vo;

/**
 * <p> 返给订单系统信息 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public class ResultPayOrder {
    //错误信息
    private String errorMsg;
    //订单号
    private String orderNo;
    //支付订单号
    private Long payNo	;
    //第三方支付号
    private String payOrder;
    //支付状态
    private Integer payStatus;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}
