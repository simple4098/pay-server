package com.yql.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringConfigured
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@ServletComponentScan
@EnableFeignClients
@EnableDiscoveryClient
/*@EnableCaching*/
public class PayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayServerApplication.class, args);
	}

}
