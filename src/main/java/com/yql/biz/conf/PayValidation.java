package com.yql.biz.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

/**
 * creator simple
 * data 2016/11/12 0012.
 */
@Configuration
public class PayValidation {

    @Bean
    public LocalValidatorFactoryBean factoryBean(MessageSource messageSource) {
        OptionalValidatorFactoryBean optionalValidatorFactoryBean = new OptionalValidatorFactoryBean();
        optionalValidatorFactoryBean.setValidationMessageSource(messageSource);
        return optionalValidatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(LocalValidatorFactoryBean validatorFactoryBean) {
        MethodValidationPostProcessor validationPostProcessor = new MethodValidationPostProcessor();
        validationPostProcessor.setValidator(validatorFactoryBean);
        return validationPostProcessor;
    }

}
