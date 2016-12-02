package com.yql.biz.constraint;

import com.yql.biz.dao.IPayOrderAccountDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author simple
 */
public class OrderNoValidator implements ConstraintValidator<OrderNo, String> {
    @Autowired
    private IPayOrderAccountDao payOrderAccountDao;

    @Override
    public void initialize(OrderNo userCode) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && payOrderAccountDao.findByOrderNo(value) != null;
    }
}
