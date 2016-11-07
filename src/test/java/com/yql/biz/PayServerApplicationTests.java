package com.yql.biz;

import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableSpringConfigured
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PayServerApplicationTests {
	@Resource
	private IPayAccountService payAccountService;

	@Test
	public void finAccount() {
		PayAccount byUserCode = payAccountService.findByUserCode("123456");
	}

}
