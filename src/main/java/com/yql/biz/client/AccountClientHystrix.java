package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户中心客户端
 * @author simple
 */
@Component
public class AccountClientHystrix  implements IAccountClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountClientHystrix.class);

    @Override
    public ResponseModel<AccountVo> getAccount(String userCode) {
        logger.debug("==========================accounting支付断路器==========================");
        return ResponseModel.ERROR();
    }

    @Override
    public ResponseModel withdrawCash(@RequestParam(name = "fee") BigDecimal fee, @RequestParam(name = "userCode") String userCode, @RequestParam(name = "orderNo") String orderNo, @RequestParam(name = "txCode") String txCode) {
        return ResponseModel.ERROR(400, "连接accounting timeout");
    }

    @Override
    public ResponseModel diamondTurnCash(@RequestParam(name = "fee") BigDecimal fee, @RequestParam(name = "userCode") String userCode, @RequestParam(name = "orderNo") String orderNo) {
        return ResponseModel.ERROR(400, "连接accounting timeout");
    }
}
