package com.yql.biz;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dto.ProblemAnswerDto;
import com.yql.biz.dto.SecurityProblemDto;
import com.yql.biz.enums.SamllPayMoney;
import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableSpringConfigured
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PayServerApplicationTests {
    @Resource
    private IPayAccountService payAccountService;
    @Resource
    private IPayAccountDao payAccountDao;

    @Test
    @Ignore
    public void finAccount() {
        PayAccount byUserCode = payAccountService.findByUserCode("123456");
    }

    @Test
    @Ignore
    public void save() {
        PayAccount byUserCode = new PayAccount();
        byUserCode.setPayPassword("1245454");
        byUserCode.setRandomCode("1212");
        byUserCode.setSamllPayMoney(SamllPayMoney.MONEY_500);
        byUserCode.setSystemPaySeq(false);
        byUserCode.setUserCode("878");
        byUserCode.setCreatedTime(new Date());
        payAccountService.savePayAccount(byUserCode);
    }

    @Test
    @Ignore
    public void update() {
        PayAccount payAccount = payAccountDao.getOne(2);
        payAccount.setUserCode("6543111");
        payAccountService.savePayAccount(payAccount);
    }

    @Test
    public void  testProblem(){
        SecurityProblemDto securityProblemDto = new SecurityProblemDto();
        securityProblemDto.setUserCode("12345678");

        List<ProblemAnswerDto> list = new ArrayList<>();
        ProblemAnswerDto problemAnswerDto = new ProblemAnswerDto();
        problemAnswerDto.setAnswer("ni ijijij");
        problemAnswerDto.setProblemId(null);
        list.add(problemAnswerDto);
        ProblemAnswerDto problemAnswerDto1 = new ProblemAnswerDto();
        problemAnswerDto1.setAnswer(null);
        problemAnswerDto1.setProblemId(10001);
        list.add(problemAnswerDto1);
        securityProblemDto.setAnswers(list);

        String s = JSON.toJSONString(securityProblemDto);
        System.out.println(s);


    }
}
