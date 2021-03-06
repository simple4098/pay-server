package com.yql.biz.service;

import com.yql.biz.model.PayAccount;
import com.yql.biz.vo.PayAccountVo;
import com.yql.biz.vo.ResetPayPasswordVo;
import com.yql.biz.vo.ResultPayPassword;

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
     * 是否设置过支付密码
     * @param userCode 用户code
     */
    ResultPayPassword isPayPassword(String userCode);

    /**
     * 保存账户信息
     * @param payAccount 支付账号对象
     * @return
     */
    PayAccount savePayAccount(PayAccount payAccount);

    /**
     * 更新支付密码
     * @param payAccount
     */
    void updatePayPassword(PayAccountVo payAccount);

    /**
     * 验证支付密码是否正确
     * @param payAccount
     */
    void validatePassword(PayAccountVo payAccount);

    /**
     * 小额支付开关
     * @param payAccount
     */
    void updatePayAccountSmallMoney(PayAccountVo payAccount);

    /**
     * 更新实名认证
     * @param payAccount
     */
    void updateRealNameAuth(PayAccount payAccount);

    /**
     * 设置账号的支付密码
     * @param payAccount
     */
    void setPayPassword(PayAccountVo payAccount);

    /**
     * 重置默认支付支付密码
     * @param userCode 用户code
     */
    void resetDefaultPayPassword(String userCode);

    /**
     * 重置支付密码
     * @param resetPayPasswordVo 修改支付密码必要参数
     */
    void resetPayPassword(ResetPayPasswordVo resetPayPasswordVo);
}
