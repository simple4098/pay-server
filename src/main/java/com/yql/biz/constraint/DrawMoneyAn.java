package com.yql.biz.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {DrawMoneyValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface DrawMoneyAn {
    String message() default "{com.yql.validation.constraints.DrawMoney.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
