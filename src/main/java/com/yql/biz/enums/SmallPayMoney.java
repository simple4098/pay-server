package com.yql.biz.enums;

/**
 * <p>小额金额</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public enum SmallPayMoney {
    MONEY_200(200),
    MONEY_500(500),
    MONEY_800(800),
    MONEY_1000(1000),
    MONEY_2000(2000);

    private Integer value;
    SmallPayMoney(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
