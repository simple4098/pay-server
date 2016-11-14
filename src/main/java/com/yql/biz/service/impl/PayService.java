package com.yql.biz.service.impl;

import com.yql.biz.conf.ExternalApi;
import com.yql.biz.model.Order;
import com.yql.biz.service.IPayService;
import com.yql.biz.support.helper.RestTemplateHelper;
import com.yql.biz.web.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p> 第三方接口实现 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
@Service("payService")
public class PayService implements IPayService {

    @Resource(name = "kunlunRestTemplate")
    private RestTemplate restTemplate;
    @Resource
    private ExternalApi externalApi;

    @Override
    public Order pay(Integer id) {
        String payUrl = externalApi.getPayUrl();
        ResponseModel<Order> kunlunResult = new RestTemplateHelper<ResponseModel<Order>>(restTemplate){}.getForObject(payUrl, id);
        Order data = kunlunResult.getData();
        return data;
    }
}
