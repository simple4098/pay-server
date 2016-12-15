package com.yql.biz.vo;

/**
 * <p> 账务中心监听对象 </p>
 *
 * @auther simple
 * data 2016/12/15 0015.
 */
public class SettlementResult {

    private String orderNo;
    private String status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
