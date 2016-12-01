package com.yql.biz.enums;

/**
 * <p> 体现状态 </p>
 * @auther simple
 * data 2016/12/1 0001.
 */
public enum DrawMoneyStatus {
    PROCESSING("处理中"),DRAW_MONEY_SUCCESS("提现成功"),DRAW_MONEY_UNSUCCESS("提现失败");
    private String desc;

    DrawMoneyStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
