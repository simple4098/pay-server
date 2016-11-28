package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.IUserCenterClient;
import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.conf.SecurityConfiguration;
import com.yql.biz.dao.IBankInfoDao;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.enums.BankCodeType;
import com.yql.biz.enums.IdentificationType;
import com.yql.biz.enums.RealNameAuthType;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.BankInfo;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.PayBankVo;
import com.yql.biz.vo.ProblemAnswerVo;
import com.yql.biz.vo.SecurityVo;
import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.request.UninstallBangBody;
import com.yql.biz.vo.pay.response.UninstallBangResponseBody;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.util.List;

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
            throw new MessageRuntimeException("error.payserver.paypassword");
        }

    }


}
