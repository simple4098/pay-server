package com.yql.biz.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 富友支付客户端
 * @author simple
 */
@FeignClient(name = "fy-server",url = "${yql.kunlun.fyCheckHost}")
public interface IFyCheckCardPayClient {

    /**
     * 验证银行卡信息
     * @param FM
     * @return
     */
    @RequestMapping(value = "/mobile_pay/checkCard/checkCard01.pay",method = RequestMethod.GET,produces = "application/xml; charset=UTF-8")
    String checkCard(@RequestParam(name = "FM") String FM);

}
