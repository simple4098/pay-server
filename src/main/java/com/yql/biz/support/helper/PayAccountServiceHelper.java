package com.yql.biz.support.helper;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.model.PayAccount;
import com.yql.biz.util.PayUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>payServiceHelper具体实现</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Component
public class PayAccountServiceHelper implements IPayAccountServiceHelper {
    @Resource
    private ApplicationConf applicationConf;

    @Override
    public void updatePayPassword(PayAccount payAccount) throws Exception {
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        try{
            String md5PassWord = PayUtil.md5PassWord(payAccount.getRandomCode(), payAccount.getPayPassword(), passwordMd5Str);
            payAccount.setPayPassword(md5PassWord);
        }catch (Exception e){
            throw new RuntimeException("error.payserver.paypassword");
        }
    }

    @Override
    public void validateOldPassword(String password, PayAccount payAccount) throws Exception {
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        String md5PassWord = null;
        try{
            md5PassWord = PayUtil.md5PassWord(payAccount.getRandomCode(), password, passwordMd5Str);
        }catch (Exception e){
            throw new RuntimeException("error.payserver.paypassword");
        }
        if (!payAccount.getPayPassword().equals(md5PassWord)){
            throw new RuntimeException("error.payserver.validate.password");
        }
    }
}
