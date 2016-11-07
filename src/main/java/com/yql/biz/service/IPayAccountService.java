package com.yql.biz.service;

import com.yql.biz.model.PayAccount;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
public interface IPayAccountService {

    PayAccount findByUserCode(String userCode);

    PayAccount savePayAccount(PayAccount payAccount);
}
