package com.yql.biz.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 富友代付客服端
 * @author simple
 */
@FeignClient(name = "fy-server",url = "${yql.kunlun.fyPayForHost}")
public interface IFyPayForClient {

    /**
     * 验证银行卡信息
     */
    @RequestMapping(value = "/req.do",method = RequestMethod.POST,produces = "charset=UTF-8")
    String payFor(@RequestParam(name = "reqtype") String reqtype, @RequestParam(name = "xml")String xml,
                  @RequestParam(name = "mac")String mac, @RequestParam(name = "merid")String merid);


   // String payFor(FyPayRequest request);

}
