package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.core.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户中心客户端
 * @author simple
 */
@FeignClient(value = "pay-accounting",fallback = AccountClientHystrix.class)
public interface IAccountClient {

    /**
     * 账户中心 查询用户账户余额信息
     * @param userCode 用户唯一编码
     */
    @RequestMapping(value = "/account/info",method = RequestMethod.GET)
    ResponseModel<AccountVo> getAccount(@RequestParam(name = "userCode") String userCode);
    /**
     * @param fee 支付金额
     * @param orderNo 订单号
     * @param payeeCode 收款人账户code
     * @param payerCode 付款人账户code
     */
    @RequestMapping(value = "/settlement/payment", method = RequestMethod.POST)
    ResponseModel payment(@RequestParam(name = "fee") BigDecimal fee, @RequestParam(name = "orderNo") String orderNo,
                          @RequestParam(name = "payeeCode") String payeeCode,
                          @RequestParam(name = "payerCode") String payerCode, @RequestParam(name = "txCode") String txCode);

}
