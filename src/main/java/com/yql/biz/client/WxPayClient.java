package com.yql.biz.client;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.response.WeiXinCloseOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinQueryOrderResponse;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.vo.pay.wx.WxOrderQueryVo;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 微信客户端
 * @author simple
 */
@Component
public class WxPayClient implements IWxPayClient {
    private static final Logger logger = LoggerFactory.getLogger(WxPayClient.class);
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;

    @Override
    public ResponseModel<WeiXinResponse> sendPrepay(@RequestParam String xml) {
        String wxprepayUrl = applicationConf.getWxPrepayUrl();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(wxprepayUrl, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            logger.debug("微信统一接口下单 resContent"+resContent);
            WeiXinResponse response = (WeiXinResponse)PlatformPayUtil.convertXmlStrToObject(WeiXinResponse.class, resContent);
            logger.debug("微信统一接口下单 【prepayId:"+response.getPrepayId()+"】");
            return ResponseModel.SUCCESS(response);
        }else {
            throw new RuntimeException("预付订单请求异常 返回状态:"+httpPost);
        }
    }

    @Override
    public ResponseModel<WeiXinQueryOrderResponse> queryWxOrder(String payNo) {
        WxOrderQueryVo wxOrderQueryVo = new WxOrderQueryVo();
        wxOrderQueryVo.setOutTradeNo(payNo);
        wxOrderQueryVo.setAppId(applicationConf.getAppid());
        wxOrderQueryVo.setMchId(applicationConf.getMchid());
        String sign = payOrderCardParamHelper.getSign(wxOrderQueryVo);
        wxOrderQueryVo.setSign(sign);
        String xml = PlatformPayUtil.payRequestXml(wxOrderQueryVo);
        String queryOrder = applicationConf.getWxQueryOrder();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(queryOrder, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            WeiXinQueryOrderResponse response = (WeiXinQueryOrderResponse)PlatformPayUtil.convertXmlStrToObject(WeiXinQueryOrderResponse.class, resContent);
            logger.info("微信查询订单返回 resContent:"+resContent);
            return ResponseModel.SUCCESS(response);
        }else {
            throw new RuntimeException("微信查询订单异常 返回状态:"+httpPost);
        }

    }

    @Override
    public ResponseModel<WeiXinCloseOrderResponse> closeOrder(@RequestParam String xml) {
        String closeOrder = applicationConf.getWxCloseOrder();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(closeOrder, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            logger.info("微信关闭订单返回 resContent:"+resContent);
            WeiXinCloseOrderResponse response = (WeiXinCloseOrderResponse)PlatformPayUtil.convertXmlStrToObject(WeiXinCloseOrderResponse.class, resContent);
            logger.debug("微信关闭订单返回 【outTradeNo:"+response.getOutTradeNo()+"】 ");
            return ResponseModel.SUCCESS(response);
        }else {
            throw new RuntimeException("微信关闭订单异常 返回状态:"+httpPost);
        }
    }
}
