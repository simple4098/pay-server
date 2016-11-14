package com.yql.biz.service;

import com.yql.biz.model.Order;

/**
 * <p> 调用第三方支付接口 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
public interface IPayService {
     Order pay(Integer id);
}
