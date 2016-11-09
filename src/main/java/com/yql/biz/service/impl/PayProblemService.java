package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayProblemDao;
import com.yql.biz.dao.ISecurityProblemDao;
import com.yql.biz.dto.PayProblemDto;
import com.yql.biz.dto.ProblemAnswerDto;
import com.yql.biz.dto.SecurityProblemDto;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayProblem;
import com.yql.biz.model.SecurityProblem;
import com.yql.biz.service.IPayProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/8 0008.
 */
@Service
@Transactional
public class PayProblemService implements IPayProblemService {
    private static final Logger logger = LoggerFactory.getLogger(PayProblemService.class);
    @Resource
    private IPayProblemDao payProblemDao;
    @Resource
    private IPayAccountDao payAccountDao;
    @Resource
    private ISecurityProblemDao securityProblemDao;
    @Resource
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public List<PayProblemDto> findPayProblemList() {
        List<PayProblemDto> list = new ArrayList<>();
        List<PayProblem> all = payProblemDao.findAll();
        for (PayProblem payProblem:all){
            PayProblemDto payProblemDto = new PayProblemDto();
            BeanUtils.copyProperties(payProblem,payProblemDto);
            list.add(payProblemDto);
        }

        return list;
    }

    @Override
    public List<SecurityProblem> saveySecurity(String json) {
        logger.debug("设置密保问题 json:"+json);
        SecurityProblemDto securityProblemDto = JSON.parseObject(json, SecurityProblemDto.class);
        PayAccount payAccount = payAccountDao.findByUserCode(securityProblemDto.getUserCode());
        Assert.notNull(payAccount,messageSourceAccessor.getMessage("error.payserver.saveySecurity.userCode"));
        List<ProblemAnswerDto> answers = securityProblemDto.getAnswers();
        List<SecurityProblem> securityProblems = new ArrayList<>();
        SecurityProblem securityProblem = null;
        for (ProblemAnswerDto problemAnswerDto: answers){
            securityProblem = new SecurityProblem();
            securityProblem.setAnswer(problemAnswerDto.getAnswer());
            securityProblem.setProblemId(problemAnswerDto.getProblemId());
            Assert.notNull(problemAnswerDto.getProblemId(),messageSourceAccessor.getMessage("error.payserver.saveySecurity.problem"));
            Assert.notNull(problemAnswerDto.getAnswer(),messageSourceAccessor.getMessage("error.payserver.saveySecurity.answer"));
            securityProblem.setPayAccountId(payAccount.getId());
            securityProblems.add(securityProblem);
        }
        return securityProblemDao.save(securityProblems);
    }
}
