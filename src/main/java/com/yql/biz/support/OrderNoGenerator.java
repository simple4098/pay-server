package com.yql.biz.support;


import com.yql.biz.enums.PayType;


public interface OrderNoGenerator {
    long generate(PayType orderType);
}
