package com.yql.biz.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>支付系统配置文件</p>
 * creator simple
 * data 2016/11/8 0008.
 */
@ConfigurationProperties(prefix = "yql")
@Configuration
public class ApplicationConf {
    private String passwordMd5Str;
    private String orderNumKey;

    public String getPasswordMd5Str() {
        return passwordMd5Str;
    }

    public void setPasswordMd5Str(String passwordMd5Str) {
        this.passwordMd5Str = passwordMd5Str;
    }

    public String getOrderNumKey() {
        return orderNumKey;
    }

    public void setOrderNumKey(String orderNumKey) {
        this.orderNumKey = orderNumKey;
    }
}
