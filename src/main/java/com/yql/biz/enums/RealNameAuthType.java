package com.yql.biz.enums;

/**
 * Created by wangdayin on 2016/10/27 0027.
 * 实名认证类型：大陆，港澳台
 */
public enum RealNameAuthType {
    MAINlAND("大陆"),
    HKMACTW("港澳台");

    private String text;

    RealNameAuthType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
