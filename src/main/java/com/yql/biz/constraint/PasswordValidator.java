package com.yql.biz.constraint;

import com.yql.core.exception.MessageRuntimeException;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

/**
 * @author simple
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isEmpty(password)) throw new MessageRuntimeException("com.yql.validation.constraints.payPassword.notnull");
            byte[] bytes = new BASE64Decoder().decodeBuffer(password);
            String payPassword = new String(bytes);
            return payPassword.matches("^[0-9]{6}$");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
