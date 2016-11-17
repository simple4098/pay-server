package com.yql.biz.client;

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

    @RequestMapping(value = "/bang_bank", method = RequestMethod.POST)
    String bangBank(String xml,String base64);
}
