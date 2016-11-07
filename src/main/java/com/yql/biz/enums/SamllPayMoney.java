package com.yql.biz.enums;

/**
 * <p>小额金额</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public enum SamllPayMoney {
    MONEY_200(200),
    MONEY_500(500),
    MONEY_800(800),
    MONEY_1000(1000),
    MONEY_2000(2000);

    private int value;
    SamllPayMoney(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}