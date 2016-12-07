package com.yql.biz.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 富友支付客户端
 * @author simple
 */

public class FyCheckCardPayClient implements IFyCheckCardPayClient {
    private static  final Logger log = LoggerFactory.getLogger(FyCheckCardPayClient.class);


    public String checkCard(String xml) {
      /*  String checkCardUrl = applicationConf.getFyCheckCardUrl();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(checkCardUrl, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            log.debug("验证银行卡 resContent"+resContent);
            CheckCardResponse response = (CheckCardResponse) PlatformPayUtil.convertXmlStrToObject(CheckCardResponse.class, resContent);

        }else {
            throw new RuntimeException("验证银行卡异常 返回状态:"+httpPost);
        }*/
        log.debug("=========================fy exceprion============================");
        return null;
    }
}
