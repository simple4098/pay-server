package com.yql.biz.enums;

import com.yql.biz.support.PayServerApplicationContext;
import com.yql.biz.support.pay.IPayOrderAccountHelper;

/**
 * <p>支付类型</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public enum PayType {

    ACCOUNT("余额支付") {
        @Override
        public IPayOrderAccountHelper createOrder() {
            return (IPayOrderAccountHelper) PayServerApplicationContext.getBean("payOrderAccountHelper");
        }
    },
    QUICK_PAYMENT("银行卡快捷支付") {
        @Override
        public IPayOrderAccountHelper createOrder() {
            return (IPayOrderAccountHelper) PayServerApplicationContext.getBean("payOrderCardHelper");
        }
    },
    DIAMOND("钻石支付"){
        @Override
        public IPayOrderAccountHelper createOrder() {
            return (IPayOrderAccountHelper) PayServerApplicationContext.getBean("payOrderDiamondHelper");
        }
    },WX_PAY("微信支付"){
        @Override
        public IPayOrderAccountHelper createOrder() {
            return (IPayOrderAccountHelper) PayServerApplicationContext.getBean("payOrderWxPayHelper");
        }
    },ALI_PAY("支付宝支付"){
        @Override
        public IPayOrderAccountHelper createOrder() {
            return (IPayOrderAccountHelper) PayServerApplicationContext.getBean("payOrderAliPayHelper");
        }
    };
    public abstract IPayOrderAccountHelper createOrder();
    private String value;

    PayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
