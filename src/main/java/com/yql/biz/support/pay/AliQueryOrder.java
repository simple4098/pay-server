package com.yql.biz.support.pay;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yql.biz.client.IAliPayClient;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.AliTradeStatus;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.ResultQueryOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 阿里查询订单实现类 </p>
 * @auther simple
 * data 2016/12/3 0003.
 */
@Component
public class AliQueryOrder implements IQueryOrder {
    @Resource
    private IAliPayClient aliPayClient;

    @Override
    public ResultQueryOrder queryOrder(PayOrderAccount payOrderVo) {
        ResultQueryOrder resultQueryOrder = new ResultQueryOrder();
        AlipayTradeQueryResponse aliPayTradeQueryResponse = aliPayClient.queryAliOrder(payOrderVo);
        resultQueryOrder.setTxCode(payOrderVo.getTxCode());
        resultQueryOrder.setPayPrice(payOrderVo.getTotalPrice());
        resultQueryOrder.setAliTradeStatus(AliTradeStatus.valueOf(aliPayTradeQueryResponse.getTradeStatus()));
        resultQueryOrder.setPayOrder(aliPayTradeQueryResponse.getTradeNo());
        resultQueryOrder.setOrderNo(payOrderVo.getOrderNo());
        resultQueryOrder.setPayStatus(payOrderVo.getPayStatus());
        return resultQueryOrder;
    }

    @Override
    public boolean supports(PayOrderAccount payOrderVo) {
        return PayType.ALI_PAY.equals(payOrderVo.getPayType());
    }
}
