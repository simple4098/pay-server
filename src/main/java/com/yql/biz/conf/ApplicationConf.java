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
public class ApplicationConf implements ExternalApi {
    private String passwordMd5Str;
    private String orderNumKey;
    //应用ID
    private String appid;
    //商户号
    private String mchid;
    //微信key
    private String wxKey;
    //微信支付 - body
    private String wxBody;
    //微信异步通知
    private String wxNotifyUrl;
    //阿里异步通知
    private String aliPayNotifyUrl;
    //监听类的topic
    private String listenerTopic;
    //发送消息的topic
    private String sendMsgTopic;
    //机构代码
    private String institutionId;
    //发送消息开关
    private boolean isOpen;
    private Kunlun kunlun;

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

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

    public Kunlun getKunlun() {
        return kunlun;
    }

    public void setKunlun(Kunlun kunlun) {
        this.kunlun = kunlun;
    }

    public String getListenerTopic() {
        return listenerTopic;
    }

    public void setListenerTopic(String listenerTopic) {
        this.listenerTopic = listenerTopic;
    }

    public String getSendMsgTopic() {
        return sendMsgTopic;
    }

    public void setSendMsgTopic(String sendMsgTopic) {
        this.sendMsgTopic = sendMsgTopic;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getWxKey() {
        return wxKey;
    }

    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }

    public String getWxNotifyUrl() {
        return wxNotifyUrl;
    }

    public void setWxNotifyUrl(String wxNotifyUrl) {
        this.wxNotifyUrl = wxNotifyUrl;
    }

    public String getAliPayNotifyUrl() {
        return aliPayNotifyUrl;
    }

    public void setAliPayNotifyUrl(String aliPayNotifyUrl) {
        this.aliPayNotifyUrl = aliPayNotifyUrl;
    }

    public String getWxBody() {
        return wxBody;
    }

    public void setWxBody(String wxBody) {
        this.wxBody = wxBody;
    }

    @Override
    public String getPayUrl() {
        return kunlun.getHost()+kunlun.getPayUri();
    }

    @Override
    public String getWxPrepayUrl() {
        return kunlun.getWxHost()+kunlun.getPrepayUri();
    }

    @Override
    public String getWxQueryOrder() {
        return kunlun.getWxHost()+kunlun.getWxQuneryUri();
    }

    @Override
    public String getWxCloseOrder() {
        return kunlun.getWxHost()+kunlun.getWxQuneryUri();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public static class Kunlun {
        //快捷支付接口host
        private String host;
        //快捷支付uri
        private String payUri;
        //微信host
        private String wxHost;
        //微信预付订单uri
        private String prepayUri;
        //微信查询订单
        private String wxQuneryUri;
        //微信关闭订单
        private String wxCloseUri;

        public String getWxCloseUri() {
            return wxCloseUri;
        }

        public void setWxCloseUri(String wxCloseUri) {
            this.wxCloseUri = wxCloseUri;
        }

        public String getWxQuneryUri() {
            return wxQuneryUri;
        }

        public void setWxQuneryUri(String wxQuneryUri) {
            this.wxQuneryUri = wxQuneryUri;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPayUri() {
            return payUri;
        }

        public void setPayUri(String payUri) {
            this.payUri = payUri;
        }

        public String getWxHost() {
            return wxHost;
        }

        public void setWxHost(String wxHost) {
            this.wxHost = wxHost;
        }

        public String getPrepayUri() {
            return prepayUri;
        }

        public void setPrepayUri(String prepayUri) {
            this.prepayUri = prepayUri;
        }
    }
}
