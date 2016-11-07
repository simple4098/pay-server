package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
@Service
public class PayAccountService implements IPayAccountService {
    @Resource
    private IPayAccountDao payAccountDao;

    @Override
    public PayAccount findByUserCode(String userCode) {
        return payAccountDao.findByUserCode(userCode);
    }

    @Override
    public PayAccount savePayAccount(PayAccount payAccount) {
        return payAccountDao.saveAndFlush(payAccount);
    }

}
