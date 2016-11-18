package com.yql.biz.support;


import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayBank;


public interface OrderNoGenerator {
    /**
     * 支付单号（pay-server中的支付号码）
     * @param orderType 订单类型
     */
    long generate(PayType orderType);

    /**
     * 绑定银行卡流水号
     */
    String txSNBinding(PayBank payBank);
    /**
     * 绑定银行卡中的交易代码
     */
    String txCode(PayBank payBank);

}
