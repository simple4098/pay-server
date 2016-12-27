package com.yql.biz.support.pay;

import com.yql.biz.client.IWxPayClient;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.WxPayResult;
import com.yql.biz.enums.pay.WxTradeState;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.ResultQueryOrder;
import com.yql.biz.vo.pay.response.WeiXinQueryOrderResponse;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 微信查询订单实现类 </p>
 * @auther simple
 * data 2016/12/3 0003.
 */
@Component
public class WxQueryOrder implements  IQueryOrder {
    private static  final Logger log = LoggerFactory.getLogger(WxQueryOrder.class);
    @Resource
    private IWxPayClient wxPayClient;
    @Override
    public ResultQueryOrder queryOrder(PayOrderAccount payOrderVo) {
        ResultQueryOrder resultQueryOrder = new ResultQueryOrder();
        try {
            ResponseModel<WeiXinQueryOrderResponse> queryWxOrder = wxPayClient.queryWxOrder(payOrderVo.getPayNo());
            if (queryWxOrder!=null){
                WeiXinQueryOrderResponse data = queryWxOrder.getData();
                if (data.getReturnCode().equals(WxPayResult.SUCCESS.name())){
                    if (data.getResultCode().equals(WxPayResult.SUCCESS.name())){
                        WxTradeState tradeState = data.getTradeState();
                        BeanUtils.copyProperties(payOrderVo, resultQueryOrder);
                        resultQueryOrder.setWxTradeState(tradeState);
                        resultQueryOrder.setTxCode(data.getOpenid());
                    }else {
                        throw  new RuntimeException(data.getErrCodeDes());
                    }
                }else {
                    throw  new RuntimeException(data.getReturnMsg());
                }
            }
        } catch (Exception e) {
            log.error("wx 查询订单异常",e);
            throw  new RuntimeException("wx 查询订单异常+"+e.getMessage());
        }
        return resultQueryOrder;
    }

    @Override
    public boolean supports(PayOrderAccount payOrderVo) {
        return PayType.WX_PAY.equals(payOrderVo.getPayType());
    }
}
