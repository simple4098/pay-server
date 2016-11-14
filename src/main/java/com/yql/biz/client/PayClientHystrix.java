package com.yql.biz.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 断路器实现类
 * @author wangxiaohong
 */
@Component
public class PayClientHystrix implements PayClient {
    @Override
    public String getUserCodeByAccount(@RequestParam("account") String destAccount) {
        return "11211211";
    }
}
