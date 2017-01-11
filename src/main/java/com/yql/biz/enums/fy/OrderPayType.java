package com.yql.biz.enums.fy;

/**
 * <p> 富友支付类型 </p>
 *
 * @auther simple
 * data 2016/12/14 0014.
 */
public enum OrderPayType {
    B2C("B2C 支付"), B2B("B2B 支付"), FYCD("预付卡"), SXF("随心富");
    private String desc;

    OrderPayType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
