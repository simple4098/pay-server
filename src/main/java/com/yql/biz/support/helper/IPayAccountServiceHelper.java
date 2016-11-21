package com.yql.biz.support.helper;

import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.vo.PayBankVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.Request;

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
    void md5PayPassword(PayAccount payAccount) ;

    /**
     * 验证旧密码是否正确
     * @param password 用户输入之前的的密码
     * @param payAccount 数据库存储支付数据
     */
    void validateOldPassword(String password,PayAccount payAccount);

    /**
     * 创建绑定银行卡必要的参数：
     */
    Param crateBangBankParam(PayBankVo payBankVo,PayBank newPayBak);

    /**
     * 根据userCode 查询payAccount。如果查询不到，就初始化一个
     * @param userCode 用户编码
     */
    PayAccount findOrCratePayAccount(String userCode);
    /**
     * 创建解绑银行卡必要的参数：
     */
    Param crateUnBangBankParam(PayBank payBank);
}
