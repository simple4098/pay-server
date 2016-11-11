package com.yql.biz.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author
 */
@Configuration
public class SecurityConfiguration {



    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public Md4PasswordEncoder md4PasswordEncoder() {
        return new Md4PasswordEncoder();
    }


}
