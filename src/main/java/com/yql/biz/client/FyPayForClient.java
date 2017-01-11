package com.yql.biz.client;

import com.alibaba.fastjson.JSON;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.util.PayHttpClientUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.fy.FyB2CPayRequest;
import com.yql.biz.vo.pay.fy.FyH5PayRequest;
import com.yql.biz.vo.pay.fy.FyPayForResponse;
import com.yql.biz.vo.pay.fy.FyPayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 富友代付接口实现
 * @author simple
 */
@Component
public class FyPayForClient implements IFyPayForClient  {
    private static final Logger log = LoggerFactory.getLogger(FyPayForClient.class);
    @Resource
    private ApplicationConf applicationConf;


    @Override
    public String payFor(@RequestParam(name = "reqtype") String reqtype, @RequestParam(name = "xml") String xml, @RequestParam(name = "mac") String mac, @RequestParam(name = "merid") String merid) {
        return null;
    }

    @Override
    public FyPayForResponse payFor(FyPayRequest fyPayRequest) {
        String payForUrl = applicationConf.getFyPayForUrl();
        try {
            log.debug("富友代付request:"+ JSON.toJSONString(fyPayRequest));
            String httpKvPost = PayHttpClientUtil.httpKvPost(payForUrl, fyPayRequest);
            log.debug("富友代付response:"+httpKvPost);
            return (FyPayForResponse) PlatformPayUtil.convertXmlStrToObject(FyPayForResponse.class,httpKvPost);
        } catch (Exception e) {
            log.error("富友代付接口异常",e);
        }
        return null;
    }

    @Override
    public String pay(FyB2CPayRequest fyB2CPayRequest) {
        String payForUrl = applicationConf.getFyB2CPayUrl();
        try {
            log.debug("富友支付url:" + payForUrl);
            log.debug("富友支付request:" + JSON.toJSONString(fyB2CPayRequest));
            String httpKvPost = PayHttpClientUtil.httpKvPost(payForUrl, fyB2CPayRequest);
            log.debug("富友支付response:" + httpKvPost);
            return httpKvPost;
            //return (FyB2CPayResponse) PlatformPayUtil.convertXmlStrToObject(FyB2CPayResponse.class,httpKvPost);
        } catch (Exception e) {
            log.error("富友代付接口异常", e);
        }
        return null;
    }


    @Override
    public String h5Pay(FyH5PayRequest fyH5PayRequest) {
        try {
            String fyB2CPayUrl = applicationConf.getFyH5PayUrl();
            log.debug("富友H5支付request:" + JSON.toJSONString(fyH5PayRequest));
            String httpKvPost = PayHttpClientUtil.httpKvPostUrl(fyB2CPayUrl, fyH5PayRequest);
            log.debug("富友H5支付response:" + httpKvPost);
            return httpKvPost;
        } catch (Exception e) {
            log.debug("富友H5支付异常", e);
        }
        return null;
    }
}
