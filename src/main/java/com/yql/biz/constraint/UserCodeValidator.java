package com.yql.biz.constraint;

import com.yql.biz.dao.IPayAccountDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author simple
 */
public class UserCodeValidator implements ConstraintValidator<UserCode, String> {
    @Autowired
    private IPayAccountDao payAccountDao;



    @Override
    public void initialize(UserCode userCode) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && payAccountDao.findByUserCode(value) != null;
    }
}
