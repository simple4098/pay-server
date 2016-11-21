package com.yql.biz.support;


import com.yql.biz.enums.BankCodeType;
import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayBank;


public interface OrderNoGenerator {
    /**
     * 支付单号（pay-server中的支付号码）
     * @param orderType 订单类型
     */
    long generate(PayType orderType);



    /**
     * 生产银行卡不同的编码
     * @param payBank 绑定银行卡对象
     * @param bankCodeType  绑定类型  解绑类型  交易类型
     */
    String generateBankCode(PayBank payBank, BankCodeType bankCodeType);

}
