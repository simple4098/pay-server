package com.yql.biz.conf;

/**
 * <p>调用第三方接口地址</p>
 * @auther simple
 * data 2016/11/14 0014.
 */
public interface ExternalApi {
    /**
     * 快捷支付 url
     */
    String getPayUrl();

    /**
     * 微信预付订单url
     */
    String getWxPrepayUrl();

    /**
     * 微信查询订单url
     */
    String getWxQueryOrder();

    /**
     * 关闭微信订单
     */
    String getWxCloseOrder();

    /**
     * 获取富友验证银行卡信息
     */
    String getFyCheckCardUrl();

    /**
     * 代付url
     */
    String getFyPayForUrl();

    /**
     * 验证身份证接口
     */
    String getFyCheckIdUrl();

    /**
     * 富友支付url
     *
     * @return
     */
    String getFyB2CPayUrl();

    /**
     * 富友H5支付
     */
    String getFyH5PayUrl();

    /**
     * 富友下单接口
     */
    String getFyCreateOrder();

    /**
     * 微信退款地址
     */
    String getWxRefundUrl();
}
