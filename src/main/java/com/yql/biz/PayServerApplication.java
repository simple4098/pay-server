package com.yql.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringConfigured
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@ServletComponentScan
public class PayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayServerApplication.class, args);
	}

}
