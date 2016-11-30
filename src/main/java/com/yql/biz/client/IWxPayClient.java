package com.yql.biz.client;

import com.yql.biz.vo.pay.response.WeiXinResponse;
import com.yql.biz.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 * @author simple
 */
/*@FeignClient(value = "order-center",fallback = WxPayClient.class)*/
public interface IWxPayClient {

    /**
     * 微信服务端 统一下单
     * @param xml
     * @return
     */
  /*  @RequestMapping(value = "/pay/unifiedorder",method = RequestMethod.POST)*/
   ResponseModel<WeiXinResponse> sendPrepay(@RequestParam String xml);

}
