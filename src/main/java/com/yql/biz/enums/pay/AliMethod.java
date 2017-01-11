package com.yql.biz.enums.pay;

/**
 * <p> ali pay 方法枚举 </p>
 * @auther simple
 * data 2016/12/21 0021.
 */
public enum AliMethod {
    ALI_APP_PAY("alipay.trade.app.pay");
    private String methodName;

    AliMethod(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
