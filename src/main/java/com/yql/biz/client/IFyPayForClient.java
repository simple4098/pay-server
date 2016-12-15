package com.yql.biz.client;

import com.yql.biz.vo.pay.fy.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 富友代付客服端
 * @author simple
 */
/*@FeignClient(name = "fy-server",url = "${yql.kunlun.fyPayForHost}")*/
public interface IFyPayForClient {

    /**
     * 验证银行卡信息
     */
    /*@RequestMapping(value = "/req.do",method = RequestMethod.POST)*/
    String payFor(@RequestParam(name = "reqtype") String reqtype, @RequestParam(name = "xml")String xml,
                  @RequestParam(name = "mac")String mac, @RequestParam(name = "merid")String merid);
    /**
     * 富友代付接口
     * @param fyPayRequest 请求接口的参数对象
     */
    FyPayForResponse payFor(FyPayRequest fyPayRequest);

    /**
     * b2c 支付
     *
     * @param fyB2CPayRequest 支付请求对象
     */
    String pay(FyB2CPayRequest fyB2CPayRequest);

    /**
     * h5支付
     *
     * @param fyH5PayRequest h5支付请求对象
     */
    String h5Pay(FyH5PayRequest fyH5PayRequest);
}
