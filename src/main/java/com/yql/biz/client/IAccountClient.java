package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账户中心客户端
 * @author simple
 */
@FeignClient(value = "pay-accounting")
public interface IAccountClient {


    @RequestMapping(value = "/account/info",method = RequestMethod.GET)
   ResponseModel<AccountVo> getAccount(@RequestParam(name = "userCode") String userCode);

}
