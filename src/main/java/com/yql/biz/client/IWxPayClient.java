package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 * @author simple
 */
@FeignClient(value = "wx-pay",url = "https://api.mch.weixin.qq.com",fallback = WxPayClient.class)
public interface IWxPayClient {


    @RequestMapping(value = "/pay/unifiedorder",method = RequestMethod.POST)
   ResponseModel<AccountVo> getAccount(@RequestParam(name = "userCode") String userCode);

}
