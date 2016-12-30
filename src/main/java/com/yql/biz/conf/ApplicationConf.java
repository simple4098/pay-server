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
    //支付宝AppId
    private String aliPayAppId;
    private String aliPayGateway;
    //支付宝SHA1
    private String aliSha1;
    //支付宝私秘
    private String aliPrivateKey;
    private String aliPubliceKey;
    //阿里异步通知
    private String aliNotifyUrl;
    //alipay ali-pay-return
    private String aliWapReturnUrl;
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
    private String accountListenerTopic;
    private String accountListenerTag;
    //机构代码
    private String institutionId;
    //商户code
    private String fyMerid;
    //手机快捷支付/银行卡验证/身份认证/企业认证交易密钥
    private String fyCheckKey;
    //商户B2C/B2B网关支付交易密钥秘钥
    private String fyTradeKey;
    //发送消息开关
    private boolean openSendMessage;
    //后台通知URL
    private String fyPayNotifyUrl;
    //页面跳转URL
    private String pageNotifyUrl;
    //hH5支付失败
    private String h5reurl;
    //定时任务线程数
    private Integer threadNum;
    private Kunlun kunlun;

    public String getAliPayAppId() {
        return aliPayAppId;
    }

    public void setAliPayAppId(String aliPayAppId) {
        this.aliPayAppId = aliPayAppId;
    }

    public String getAliPayGateway() {
        return aliPayGateway;
    }

    public void setAliPayGateway(String aliPayGateway) {
        this.aliPayGateway = aliPayGateway;
    }

    public String getAliSha1() {
        return aliSha1;
    }

    public void setAliSha1(String aliSha1) {
        this.aliSha1 = aliSha1;
    }

    public String getAliPrivateKey() {
        return aliPrivateKey;
    }

    public void setAliPrivateKey(String aliPrivateKey) {
        this.aliPrivateKey = aliPrivateKey;
    }

    public String getAliPubliceKey() {
        return aliPubliceKey;
    }

    public void setAliPubliceKey(String aliPubliceKey) {
        this.aliPubliceKey = aliPubliceKey;
    }

    public String getAliNotifyUrl() {
        return aliNotifyUrl;
    }

    public void setAliNotifyUrl(String aliNotifyUrl) {
        this.aliNotifyUrl = aliNotifyUrl;
    }

    public String getAliWapReturnUrl() {
        return aliWapReturnUrl;
    }

    public void setAliWapReturnUrl(String aliWapReturnUrl) {
        this.aliWapReturnUrl = aliWapReturnUrl;
    }

    public String getFyPayNotifyUrl() {
        return fyPayNotifyUrl;
    }

    public void setFyPayNotifyUrl(String fyPayNotifyUrl) {
        this.fyPayNotifyUrl = fyPayNotifyUrl;
    }

    public String getPageNotifyUrl() {
        return pageNotifyUrl;
    }

    public void setPageNotifyUrl(String pageNotifyUrl) {
        this.pageNotifyUrl = pageNotifyUrl;
    }

    public String getH5reurl() {
        return h5reurl;
    }

    public void setH5reurl(String h5reurl) {
        this.h5reurl = h5reurl;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

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

    public String getFyMerid() {

        return fyMerid;
    }

    public void setFyMerid(String fyMerid) {
        this.fyMerid = fyMerid;
    }

    public String getFyCheckKey() {
        return fyCheckKey;
    }

    public void setFyCheckKey(String fyCheckKey) {
        this.fyCheckKey = fyCheckKey;
    }

    public String getFyTradeKey() {
        return fyTradeKey;
    }

    public void setFyTradeKey(String fyTradeKey) {
        this.fyTradeKey = fyTradeKey;
    }

    public String getAccountListenerTopic() {
        return accountListenerTopic;
    }

    public void setAccountListenerTopic(String accountListenerTopic) {
        this.accountListenerTopic = accountListenerTopic;
    }

    public String getAccountListenerTag() {
        return accountListenerTag;
    }

    public void setAccountListenerTag(String accountListenerTag) {
        this.accountListenerTag = accountListenerTag;
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

    @Override
    public String getFyCheckCardUrl() {
        return kunlun.getFyCheckHost()+kunlun.getCheckCardUri();
    }

    @Override
    public String getFyPayForUrl() {
        return kunlun.getFyPayForHost()+kunlun.getPayForUri();
    }

    @Override
    public String getFyCheckIdUrl() {
        return kunlun.getFyCheckHost()+kunlun.getCheckIdUri();
    }

    @Override
    public String getFyB2CPayUrl() {
        return kunlun.getFyB2CPayHost() + kunlun.getFyPayUri();
    }

    @Override
    public String getFyH5PayUrl() {
        return kunlun.getFyCheckHost() + kunlun.getFyH5PayUri();
    }

    @Override
    public String getFyCreateOrder() {
        return kunlun.getFyCheckHost() + kunlun.getFyCreateOrderUri();
    }

    @Override
    public String getWxRefundUrl() {
        return kunlun.getWxHost()+kunlun.getWxRefundUri();
    }

    public boolean getOpenSendMessage() {
        return openSendMessage;
    }

    public void setOpenSendMessage(boolean openSendMessage) {
        this.openSendMessage = openSendMessage;
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
        //富友host
        private String fyCheckHost;
        //富友验证银行卡uri
        private String checkCardUri;
        //代付host
        private String fyPayForHost;
        //代付uti
        private String payForUri;
        //验证身份证uri
        private String checkIdUri;
        //支付host
        private String fyB2CPayHost;
        //富友支付uri
        private String fyPayUri;
        //h5支付uri
        private String fyH5PayUri;
        //富友下单uri
        private String fyCreateOrderUri;
        //微信退款uri
        private String wxRefundUri;

        public String getFyB2CPayHost() {
            return fyB2CPayHost;
        }

        public void setFyB2CPayHost(String fyB2CPayHost) {
            this.fyB2CPayHost = fyB2CPayHost;
        }

        public String getFyPayUri() {
            return fyPayUri;
        }

        public void setFyPayUri(String fyPayUri) {
            this.fyPayUri = fyPayUri;
        }

        public String getFyH5PayUri() {
            return fyH5PayUri;
        }

        public void setFyH5PayUri(String fyH5PayUri) {
            this.fyH5PayUri = fyH5PayUri;
        }

        public String getFyCreateOrderUri() {
            return fyCreateOrderUri;
        }

        public void setFyCreateOrderUri(String fyCreateOrderUri) {
            this.fyCreateOrderUri = fyCreateOrderUri;
        }

        public String getWxRefundUri() {
            return wxRefundUri;
        }

        public void setWxRefundUri(String wxRefundUri) {
            this.wxRefundUri = wxRefundUri;
        }

        public String getCheckIdUri() {
            return checkIdUri;
        }

        public void setCheckIdUri(String checkIdUri) {
            this.checkIdUri = checkIdUri;
        }

        public String getFyCheckHost() {
            return fyCheckHost;
        }

        public void setFyCheckHost(String fyCheckHost) {
            this.fyCheckHost = fyCheckHost;
        }

        public String getFyPayForHost() {
            return fyPayForHost;
        }

        public void setFyPayForHost(String fyPayForHost) {
            this.fyPayForHost = fyPayForHost;
        }

        public String getPayForUri() {
            return payForUri;
        }

        public void setPayForUri(String payForUri) {
            this.payForUri = payForUri;
        }

        public String getCheckCardUri() {
            return checkCardUri;
        }

        public void setCheckCardUri(String checkCardUri) {
            this.checkCardUri = checkCardUri;
        }

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
