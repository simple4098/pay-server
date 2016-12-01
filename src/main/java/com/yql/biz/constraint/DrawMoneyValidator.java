package com.yql.biz.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @author simple
 */
public class DrawMoneyValidator implements ConstraintValidator<DrawMoneyAn, BigDecimal> {




    @Override
    public void initialize(DrawMoneyAn drawMoneyAn) {

    }

    @Override
    public boolean isValid(BigDecimal money, ConstraintValidatorContext constraintValidatorContext) {

        return money != null && money.compareTo(new BigDecimal(100))!=-1;
    }
}
