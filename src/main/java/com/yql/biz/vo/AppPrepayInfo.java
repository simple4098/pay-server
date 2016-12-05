package com.yql.biz.vo;

/**
 * <p> app预付订单 </p>
 * @auther simple
 * data 2016/12/5 0005.
 */
public class AppPrepayInfo {
    private String prepayInfo;

    public AppPrepayInfo(String prepayInfo) {
        this.prepayInfo = prepayInfo;
    }

    public String getPrepayInfo() {
        return prepayInfo;
    }

    public void setPrepayInfo(String prepayInfo) {
        this.prepayInfo = prepayInfo;
    }
}
