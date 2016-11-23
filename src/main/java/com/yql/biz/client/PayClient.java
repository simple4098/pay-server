package com.yql.biz.client;

import com.yql.biz.vo.pay.response.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用大金支付平台接口
 * @author simple
 */
@FeignClient(value = "pay-platform",url = "${pay-server.url}",fallback = PayClientHystrix.class)
public interface PayClient {

    /**
     * 绑定银行卡
     * @param message 请求报文并base64编码，
     * @param signature 请求报文加密后的密文
     */
    @RequestMapping(value = "/bang_bank", method = RequestMethod.POST)
    BangResponse bangBank(@RequestParam(name = "message") String message, @RequestParam("signature") String signature);

    /**
     * 解绑定银行卡
     * @param message 请求报文并base64编码，
     * @param signature 请求报文加密后的密文
     */
    @RequestMapping(value = "/uninstall/bank", method = RequestMethod.POST)
    UninstallBangResponse uninstallBangBank(@RequestParam(name = "message") String message, @RequestParam("signature") String signature);
    /**
     * 支付
     * @param message 请求报文并base64编码
     * @param signature 请求报文加密后的密文
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST,produces = "application/xml; charset=UTF-8")
    PayMessageValidateResponse pay(@RequestParam(name = "message") String message, @RequestParam("signature") String signature);

}
