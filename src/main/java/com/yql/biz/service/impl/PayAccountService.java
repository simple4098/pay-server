package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.vo.PayAccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    @Override
    @Transactional(readOnly = true)
    public PayAccount findByUserCode(String userCode) {
        return payAccountDao.findByUserCode(userCode);
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
        payAccountServiceHelper.validateOldPassword(payAccountVo.getOldPayPassword(),one);
        PayAccount payAccount = PayAccountVo.voToDomain(payAccountVo,one);
        payAccountServiceHelper.md5PayPassword(payAccount);
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
            payAccountServiceHelper.md5PayPassword(payAccount1);
            payAccount.setPayPassword(payAccount1.getPayPassword());
        } catch (Exception e) {
            throw new MessageRuntimeException("error.payserver.paypassword");
        }
         payAccountDao.saveAndFlush(payAccount);
    }

    @Override
    public void validatePassword(PayAccount payAccount) {
        logger.debug("验证密码 userCode:"+payAccount.getUserCode());
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        payAccountServiceHelper.validateOldPassword(payAccount.getPayPassword(),one);
    }

    @Override
    public void updatePayAccountSamllMoney(PayAccount payAccount) {
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        one.setSmallPay(payAccount.isSmallPay());
        one.setSamllPayMoney(payAccount.getSamllPayMoney());
        payAccountDao.save(one);
    }

    @Override
    public void updateRealNameAuth(PayAccount payAccount) {
        PayAccount one = payAccountServiceHelper.findOrCratePayAccount(payAccount.getUserCode());
        one.setRealNameAuth(payAccount.isRealNameAuth());
        payAccountDao.saveAndFlush(one);
    }


}
