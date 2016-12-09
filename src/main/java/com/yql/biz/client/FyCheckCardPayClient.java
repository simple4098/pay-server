package com.yql.biz.client;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.util.PayHttpClientUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.fy.CheckCardResponse;
import com.yql.biz.vo.pay.fy.CheckIDCardResponse;
import com.yql.biz.vo.pay.fy.FyPayForResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 富友支付客户端
 * @author simple
 */
@Component
public class FyCheckCardPayClient implements IFyCheckCardPayClient {
    private static  final Logger log = LoggerFactory.getLogger(FyCheckCardPayClient.class);
    @Resource
    private ApplicationConf applicationConf;

    public CheckCardResponse checkCard(String xml) {
        try {
            log.debug("验证银行卡request :"+xml);
            String encode = URLEncoder.encode(xml, "utf-8");
            StringBuffer stringBuffer = new StringBuffer(applicationConf.getFyCheckCardUrl()).append("?FM=").append(encode);
            log.debug("验证银行卡request URLEncoder:"+stringBuffer.toString());
            String httpGets = PayHttpClientUtil.httpGets(stringBuffer.toString());
            log.debug("验证银行卡response:"+httpGets);
            return (CheckCardResponse) PlatformPayUtil.convertXmlStrToObject(CheckCardResponse.class,httpGets);
        } catch (IOException e) {
            log.error("验证银行卡异常",e);
        }
        return null;
    }

    @Override
    public CheckIDCardResponse checkIDCard( String fm) {
        try {
            log.debug("验证身份证request :"+fm);
            String encode = URLEncoder.encode(fm, "utf-8");
            StringBuffer stringBuffer = new StringBuffer(applicationConf.getFyCheckIdUrl()).append("?FM=").append(encode);
            log.debug("验证身份证request URLEncoder:"+stringBuffer.toString());
            String httpGets = PayHttpClientUtil.httpGets(stringBuffer.toString());
            log.debug("验证身份证response:"+httpGets);
            return (CheckIDCardResponse) PlatformPayUtil.convertXmlStrToObject(CheckIDCardResponse.class,httpGets);
        } catch (IOException e) {
            log.error("验证身份证卡异常",e);
        }
        return null;
    }
}
