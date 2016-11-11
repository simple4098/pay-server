package com.yql.biz.support.helper;

import com.yql.biz.model.PayAccount;

/**
 * <p>支付账户helper</p>
 * creator simple
 * data 2016/11/10 0010.
 */
public interface IPayAccountServiceHelper {

    /**
     * 加密支付密码
     * @param payAccount
     * @throws Exception 加密异常
     */
    void updatePayPassword(PayAccount payAccount) ;

    /**
     * 验证旧密码是否正确
     * @param password 用户输入之前的的密码
     * @param payAccount 数据库存储支付数据
     */
    void validateOldPassword(String password,PayAccount payAccount);
}
