package com.yql.biz.support.constants;

/**
 * <p> 支付共用常量类 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
public class PayConstants {
    //富友返回验证银行卡成功字符串
    public static final String FY_CHECK_CARD_SUCCESS ="0000";
    //富友返回代付成功字符串
    public static final String FY_PAY_FOR_SUCCESS ="000000";
    //富友借记卡
    public static final String CTP01="01";
    //富友信用卡
    public static final String CTP02="02";
    //请求状态成功
    public static final Integer SUCCESS = 200;
    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    public static final String ALI_APP_PRODUCT_CODE="QUICK_MSECURITY_PAY";
    //重置密码
    public static final String PAY_PASSWORD="123456";
}
