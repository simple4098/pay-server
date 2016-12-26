package com.yql.biz.constraint;

import com.yql.biz.model.PayAccount;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author simple
 */
public class UserCodeValidator implements ConstraintValidator<UserCode, String> {
    private static final  Logger logger = LoggerFactory.getLogger(UserCodeValidator.class);
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;



    @Override
    public void initialize(UserCode userCode) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        logger.debug("UserCodeValidator验证userCode:"+value);
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(value);
        return value != null && payAccount != null;
    }
}
