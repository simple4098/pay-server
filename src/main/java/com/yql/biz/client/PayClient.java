package com.yql.biz.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiaohong
 */
@FeignClient("localhost:8080")
public interface PayClient {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String getUserCodeByAccount(@RequestParam("account") String destAccount);
}
