package com.yql.biz.client;

import com.yql.biz.vo.pay.fy.CheckCardResponse;
import com.yql.biz.vo.pay.fy.CheckIDCardResponse;

/**
 * 富友支付客户端
 * @author simple
 */
/*@FeignClient(name = "fy-server",url = "${yql.kunlun.fyCheckHost}")*/
public interface IFyCheckCardPayClient {

    /**
     * 验证银行卡信息
     * @param fm
     * @return
     */
/*    @RequestMapping(value = "/mobile_pay/checkCard/checkCard01.pay",method = RequestMethod.GET)*/
    CheckCardResponse checkCard( String fm);

    CheckIDCardResponse checkIDCard(String fm);

}
