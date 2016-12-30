package com.yql.biz.client;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.pay.AliWapVersion;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.vo.pay.ali.AliPayBizRequest;
import com.yql.biz.vo.pay.ali.AliQueryOrderRequest;
import com.yql.biz.vo.pay.ali.AliRefundOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> wap支付宝 </p>
 * @auther simple
 * data 2016/12/20 0020.
 */
@Component
public class AliPayClient implements IAliPayClient {
    private static Logger logger = LoggerFactory.getLogger(AliPayClient.class);
    @Resource
    private ApplicationConf applicationConf;

    @Override
    public String aliWapFrom(PayOrderAccount payOrderAccount) {
        AliPayBizRequest aliPayBizRequest = new AliPayBizRequest();
        aliPayBizRequest.setOut_trade_no(payOrderAccount.getPayNo());
        aliPayBizRequest.setProduct_code("QUICK_WAP_PAY");
        aliPayBizRequest.setTotal_amount(payOrderAccount.getTotalPrice().toString());
        aliPayBizRequest.setSubject(applicationConf.getWxBody());
        aliPayBizRequest.setTimeout_express("1h");
        AlipayClient alipayClient = new DefaultAlipayClient(applicationConf.getAliPayGateway(),applicationConf.getAliPayAppId(),applicationConf.getAliPrivateKey());
        AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
        aliPayRequest.setApiVersion(AliWapVersion.ALI_VERSION.getValue());
        aliPayRequest.setNotifyUrl(applicationConf.getAliNotifyUrl());
        aliPayRequest.setReturnUrl(applicationConf.getAliWapReturnUrl());
        aliPayRequest.setBizContent(JSON.toJSONString(aliPayBizRequest));
        try{
            return alipayClient.pageExecute(aliPayRequest).getBody(); //调用SDK生成表单
        }catch (Exception e){
            logger.error("支付宝wap支付下单失败",e);
            throw new  RuntimeException("支付宝wap支付下单失败 ");
        }
    }

    @Override
    public AlipayTradeQueryResponse queryAliOrder(PayOrderAccount payOrderVo) {
        logger.debug("ali pay 订单查询:【"+payOrderVo.getOrderNo()+"】");
        AliQueryOrderRequest aliQueryOrderRequest = new AliQueryOrderRequest(payOrderVo.getPayNo(),payOrderVo.getPayOrder());
        AlipayClient alipayClient = new DefaultAlipayClient(applicationConf.getAliPayGateway(),applicationConf.getAliPayAppId(),applicationConf.getAliPrivateKey());
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setApiVersion(AliWapVersion.ALI_VERSION.getValue());
        request.setBizContent(JSON.toJSONString(aliQueryOrderRequest));
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            logger.debug("ali pay 订单查询 response:"+JSON.toJSONString(response));
            return  response;
        } catch (AlipayApiException e) {
            logger.debug("支付宝支付订单查询异常",e);
            throw new RuntimeException("支付宝支付订单查询异常");
        }
    }

    @Override
    public AlipayTradeFastpayRefundQueryResponse refundOrder(PayOrderAccount payOrderVo) {
        logger.debug("ali pay 订单退款:【"+payOrderVo.getOrderNo()+"】");
        AliRefundOrderRequest aliQueryOrderRequest = new AliRefundOrderRequest(payOrderVo.getPayNo(),payOrderVo.getPayOrder(),payOrderVo.getRefundNo());
        AlipayClient alipayClient = new DefaultAlipayClient(applicationConf.getAliPayGateway(),applicationConf.getAliPayAppId(),applicationConf.getAliPrivateKey());
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setApiVersion(AliWapVersion.ALI_VERSION.getValue());
        request.setBizContent(JSON.toJSONString(aliQueryOrderRequest));
        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            logger.debug("ali pay 订单退款 response:"+JSON.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            logger.debug("支付宝订单退款",e);
            throw new RuntimeException("支付宝订单退款");
        }
    }
}
