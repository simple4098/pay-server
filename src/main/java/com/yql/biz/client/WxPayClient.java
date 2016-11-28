package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 * @author simple
 */
@Component
public class WxPayClient implements IWxPayClient {


    @Override
    public ResponseModel<AccountVo> getAccount(@RequestParam(name = "userCode") String userCode) {
        return null;
    }
}
