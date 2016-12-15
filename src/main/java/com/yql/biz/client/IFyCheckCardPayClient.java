package com.yql.biz.client;

import com.yql.biz.vo.pay.fy.CheckCardResponse;
import com.yql.biz.vo.pay.fy.CheckIDCardResponse;
import com.yql.biz.vo.pay.fy.FyOrderResponse;

/**
 * 富友支付客户端
 * @author simple
 */
public interface IFyCheckCardPayClient {

    /**
     * 验证银行卡信息
     * @param fm
     * @return
     */
    CheckCardResponse checkCard( String fm);

    /**
     * 身份证验证
     *
     * @param fm 提交xml数据
     */
    CheckIDCardResponse checkIDCard(String fm);

    /**
     * 富友下单接口
     *
     * @param fm 下单xml数据
     */
    FyOrderResponse fyCreatedOrder(String fm);

}
