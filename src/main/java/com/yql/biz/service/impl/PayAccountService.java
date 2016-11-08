package com.yql.biz.service.impl;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.biz.util.PayUtil;
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
    @Resource
    private IPayAccountDao payAccountDao;
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private MessageSourceAccessor messageSourceAccessor;
    @Override
    public PayAccount findByUserCode(String userCode) {
        return payAccountDao.findByUserCode(userCode);
    }

    @Override
    public PayAccount savePayAccount(PayAccount payAccount) {
        Assert.notNull(payAccount.getUserCode(),messageSourceAccessor.getMessage("error.payserver.param.usercode"));
        Assert.notNull(payAccount.getPayPassword(),messageSourceAccessor.getMessage("error.payserver.param.paypassword"));
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        try {
            String md5PassWord = PayUtil.md5PassWord(payAccount.getRandomCode(), payAccount.getPayPassword(), passwordMd5Str);
            payAccount.setPayPassword(md5PassWord);
        } catch (Exception e) {
            throw new MessageRuntimeException("error.payserver.paypassword");
        }
        return payAccountDao.saveAndFlush(payAccount);
    }

    @Override
    public void updatePayAccount(PayAccount payAccount) {
        PayAccount one = payAccountDao.getOne(payAccount.getId());
        one.setUserCode(payAccount.getUserCode());
        payAccountDao.saveAndFlush(one);
    }


}
