package com.yql.biz.client;

import com.squareup.okhttp.OkHttpClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.web.ResponseModel;
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
    @Override
    public ResponseModel<WeiXinResponse> sendPrepay(@RequestParam String xml) {
        String wxprepayUrl = applicationConf.getWxprepayUrl();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(wxprepayUrl, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            WeiXinResponse response = (WeiXinResponse)PlatformPayUtil.convertXmlStrToObject(WeiXinResponse.class, resContent);
            logger.debug("====resContent=====:"+resContent);
            return ResponseModel.SUCCESS(response);
        }else {
            throw new RuntimeException("wx pay 预付订单请求异常 httpPost:"+httpPost);
        }
    }
}
