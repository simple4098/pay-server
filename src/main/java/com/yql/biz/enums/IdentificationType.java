package com.yql.biz.enums;

/**
 * <p> 证件类型 </p>
 * @auther simple
 * data 2016/11/18 0018.
 */
public enum IdentificationType {
    ID_CARD(0),HKMACTW(5);
    private Integer value;

    IdentificationType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
