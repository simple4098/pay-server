package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户中心客户端
 * @author simple
 */
@FeignClient(value = "pay-accounting")
public interface IAccountClient {


    /**
     * 账户中心 查询用户账户余额信息
     *
     * @param userCode 用户唯一编码
     */
    @RequestMapping(value = "/account/info",method = RequestMethod.GET)
    ResponseModel<AccountVo> getAccount(@RequestParam(name = "userCode") String userCode);

    /**
     * 体现
     *
     * @param fee      余额
     * @param userCode 用户唯一编码
     * @param orderNo  订单号
     * @param txCode   绑定的银行卡编码
     * @return
     */
    @RequestMapping(value = "/settlement/withdraw-cash", method = RequestMethod.POST)
    ResponseModel withdrawCash(@RequestParam(name = "fee") BigDecimal fee, @RequestParam(name = "userCode") String userCode,
                               @RequestParam(name = "orderNo") String orderNo, @RequestParam(name = "txCode") String txCode);

    /**
     * 钻石转余额
     *
     * @param fee      钻石余额
     * @param userCode 用户编码
     * @param orderNo  订单号
     */
    @RequestMapping(value = "/settlement/diamond-turn-cash", method = RequestMethod.POST)
    ResponseModel diamondTurnCash(@RequestParam(name = "fee") BigDecimal fee, @RequestParam(name = "userCode") String userCode,
                                  @RequestParam(name = "orderNo") String orderNo);


}
