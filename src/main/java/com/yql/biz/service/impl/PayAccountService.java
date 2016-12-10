package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.vo.PayAccountVo;
import com.yql.biz.vo.ResultPayPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
@Transactional
@Service
public class PayAccountService implements IPayAccountService {
    private static final Logger logger = LoggerFactory.getLogger(PayAccountService.class);
    @Resource
    private IPayAccountDao payAccountDao;
    @Resource
    private MessageSourceAccessor messageSourceAccessor;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;

    @Override
    @Transactional(readOnly = true)
    public PayAccount findByUserCode(String userCode) {
        return payAccountDao.findByUserCode(userCode);
    }

    @Override
    @Transactional
    public ResultPayPassword isPayPassword(String userCode) {
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(userCode);
        boolean sign = !StringUtils.isEmpty(payAccount.getPayPassword());
        return new ResultPayPassword(sign,userCode);
    }

    @Override
    public PayAccount savePayAccount(PayAccount payAccount) {
        logger.debug("初始化支付账户:"+payAccount.getUserCode());
        return payAccountDao.saveAndFlush(payAccount);
    }

    @Override
    public void updatePayPassword(PayAccountVo payAccountVo) {
        logger.debug("更新密码:"+payAccountVo.getUserCode());
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccountVo.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payAccountVo.getOldPayPassword(),one);
        PayAccount payAccount = PayAccountVo.voToDomain(payAccountVo,one);
        payPasswordSecurityHelper.md5PayPassword(payAccount);
        one.setPayPassword(payAccount.getPayPassword());
        payAccountDao.save(one);
    }

    @Override
    public void setPayPassword(PayAccountVo payAccountVo) {
        logger.debug("设置支付密码:"+payAccountVo.getUserCode());
        Assert.notNull(payAccountVo.getPayPassword(),messageSourceAccessor.getMessage("error.payserver.param.paypassword"));
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(payAccountVo.getUserCode());
        PayAccount payAccount1 = PayAccountVo.voToDomain(payAccountVo,payAccount);
        try {
            payPasswordSecurityHelper.md5PayPassword(payAccount1);
            payAccount.setPayPassword(payAccount1.getPayPassword());
        } catch (Exception e) {
            throw new MessageRuntimeException("error.payserver.paypassword");
        }
         payAccountDao.saveAndFlush(payAccount);
    }

    @Override
    public void validatePassword(PayAccountVo payAccount) {
        logger.debug("验证密码 userCode:"+payAccount.getUserCode());
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payAccount.getPayPassword(),one);
    }

    @Override
    public void updatePayAccountSmallMoney(PayAccountVo payAccount) {
        //验证支付密码
        validatePassword(payAccount);
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        one.setSmallPay(payAccount.isSmallPay());
        one.setSmallPayMoney(payAccount.getSmallPayMoney());
        payAccountDao.save(one);
    }

    @Override
    public void updateRealNameAuth(PayAccount payAccount) {
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        one.setRealNameAuth(payAccount.isRealNameAuth());
        payAccountDao.saveAndFlush(one);
    }


}
