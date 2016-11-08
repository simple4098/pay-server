package com.yql.biz.service;

import com.yql.biz.model.PayAccount;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
public interface IPayAccountService {

    /**
     * 根据userCode查询支付账号信息
     * @param userCode 用户code
     */
    PayAccount findByUserCode(String userCode);

    /**
     * 保存
     * @param payAccount 支付账号对象
     * @return
     */
    PayAccount savePayAccount(PayAccount payAccount);

    void updatePayAccount(PayAccount payAccount);
}
