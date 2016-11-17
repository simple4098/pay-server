package com.yql.biz.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 断路器实现类
 * @author simple
 */
@Component
public class PayClientHystrix implements PayClient {


    @Override
    public String bangBank(String xml, String base64) {
        return null;
    }
}
