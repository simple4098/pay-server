package com.yql.biz.client;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.util.PayHttpClientUtil;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.fy.FyPayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 富友代付客服端
 * @author simple
 */
/*@FeignClient(name = "fy-server",url = "${yql.kunlun.fyPayForHost}")*/

public class FyPayForClient  {
    private static final Logger log = LoggerFactory.getLogger(FyPayForClient.class);
    @Resource
    private ApplicationConf applicationConf;

   // @Override
    public String checkCard(String xml) {
        String checkCardUrl = applicationConf.getFyCheckCardUrl();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        boolean httpPost = httpClient.callHttpPost(checkCardUrl, xml);
        if (httpPost) {
            String resContent = httpClient.getResContent();
            log.debug("代付 resContent"+resContent);
            return resContent;

        }else {
            throw new RuntimeException("代付 返回状态:"+httpPost);
        }
    }

    //@Override
    public String checkCard(FyPayRequest request) {
        String checkCardUrl = applicationConf.getFyCheckCardUrl();
        try {
            String httpKvPost = PayHttpClientUtil.httpKvPost(checkCardUrl, request);
            return httpKvPost;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
