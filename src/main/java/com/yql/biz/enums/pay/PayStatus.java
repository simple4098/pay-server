package com.yql.biz.enums.pay;

/**
 * <p> 支付状态 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public enum PayStatus {
    HANDLING("支付中",10),PAY_SUCCESS("支付成功",20),PAY_UNSUCCESS("支付失败",30);
    private int value;
    private String desc;

    PayStatus(String desc,int value) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
