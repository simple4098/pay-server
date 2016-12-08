package com.yql.biz.client;

import com.yql.biz.vo.pay.fy.CheckCardResponse;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 富友支付客户端
 * @author simple
 */
/*@FeignClient(name = "fy-server",url = "${yql.kunlun.fyCheckHost}")*/
public interface IFyCheckCardPayClient {

    /**
     * 验证银行卡信息
     * @param FM
     * @return
     */
/*    @RequestMapping(value = "/mobile_pay/checkCard/checkCard01.pay",method = RequestMethod.GET)*/
    CheckCardResponse checkCard(@RequestParam(name = "FM") String FM);

}
