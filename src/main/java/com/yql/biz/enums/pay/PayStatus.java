package com.yql.biz.enums.pay;

/**
 * <p> 支付/提现状态 </p>
 * @auther simple
 * data 2016/11/23 0023.
 */
public enum PayStatus {
    HANDLING("处理中",10),PAY_SUCCESS("支付成功",20),PAY_UNSUCCESS("支付失败",30),WX_PAY_UNIFIED_ORDER("微信预付订单",40);
   /* DRAW_MONEY_HANDLING("提现处理中",50), DRWA_MONEY_SUCCESS("提现成功",60),DRAW_MONEY_UNSUCCESS("提现失败",70);*/
    private Integer value;
    private String desc;

    PayStatus(String desc,int value) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
