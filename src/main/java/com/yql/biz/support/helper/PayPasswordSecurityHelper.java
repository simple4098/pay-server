package com.yql.biz.support.helper;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.conf.SecurityConfiguration;
import com.yql.biz.model.PayAccount;
import com.yql.core.exception.MessageRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;

/**
 * <p>密码加密，解密 实现方式</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Component
public class PayPasswordSecurityHelper {
    private static final Logger logger = LoggerFactory.getLogger(PayPasswordSecurityHelper.class);
    @Resource
    private ApplicationConf applicationConf;
    @Resource
    private SecurityConfiguration securityConfiguration;


    public void md5PayPassword(PayAccount payAccount)  {
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        try{
            String base64 = payAccount.getPayPassword();
            byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
            String payPassword = new String(bytes);
            Md5PasswordEncoder md5PasswordEncoder = securityConfiguration.md5PasswordEncoder();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(passwordMd5Str);
            stringBuffer.append(payPassword);
            String encodePassword = md5PasswordEncoder.encodePassword(stringBuffer.toString(), payAccount.getRandomCode());
            payAccount.setPayPassword(encodePassword);
        }catch (Exception e){
            logger.error("设置密码异常：",e);
            throw new RuntimeException("error.payserver.paypassword");
        }
    }



    public void validateOldPassword(String password, PayAccount payAccount)  {
        if (StringUtils.isEmpty(password)) throw new MessageRuntimeException("error.payserver.param.paypassword");
        String passwordMd5Str = applicationConf.getPasswordMd5Str();
        try{
            byte[] bytes = new BASE64Decoder().decodeBuffer(password);
            String payPassword = new String(bytes);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append( passwordMd5Str).append(payPassword);
            Md5PasswordEncoder md5PasswordEncoder = securityConfiguration.md5PasswordEncoder();
            boolean passwordValid = md5PasswordEncoder.isPasswordValid(payAccount.getPayPassword(),stringBuffer.toString(), payAccount.getRandomCode());
            if (!passwordValid){
                throw new MessageRuntimeException("error.payserver.validate.password");
            }
        }catch (Exception e){
            logger.debug("验证密码异常",e);
            throw new RuntimeException(e.getMessage());
        }

    }


}
