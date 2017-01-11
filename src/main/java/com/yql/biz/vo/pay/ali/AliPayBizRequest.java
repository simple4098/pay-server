package com.yql.biz.vo.pay.ali;

/**
 * <p> 支付宝支付 </p>
 * @auther simple
 * data 2016/12/16 0016.
 */
public class AliPayBizRequest {

    //商品的标题/交易标题/订单标题/订单关键字等
    private String subject;
   //商户网站唯一订单号
    private String out_trade_no;
    //该笔订单允许的最晚付款时间，逾期将关闭交易。m-分钟，h-小时，d-天，1c-当天
    private String timeout_express;
    //订单总金额，单位为元
    private String total_amount;
    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    private String product_code;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
