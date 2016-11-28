package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 * @author simple
 */
@Component
public class WxPayClient implements IWxPayClient {
     private static final Logger loger = LoggerFactory.getLogger(WxPayClient.class);

    @Override
    public ResponseModel<AccountVo> pay(@RequestParam String xml) {
        loger.debug("===============wxPay error===============");
        return null;
    }
}
