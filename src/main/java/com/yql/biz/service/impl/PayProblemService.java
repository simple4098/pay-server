package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayProblemDao;
import com.yql.biz.dao.ISecurityProblemDao;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayProblem;
import com.yql.biz.model.SecurityProblem;
import com.yql.biz.service.IPayProblemService;
import com.yql.biz.vo.PayProblemDto;
import com.yql.biz.vo.ProblemAnswerVo;
import com.yql.biz.vo.SecurityProblemVo;
import com.yql.biz.vo.SecurityVo;
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
        SecurityVo securityVo = JSON.parseObject(json, SecurityVo.class);
        PayAccount payAccount = payAccountDao.findByUserCode(securityVo.getUserCode());
        Assert.notNull(payAccount,messageSourceAccessor.getMessage("error.payserver.saveySecurity.userCode"));
        List<ProblemAnswerVo> answers = securityVo.getAnswers();
        List<SecurityProblem> securityProblems = new ArrayList<>();
        SecurityProblem securityProblem = null;
        for (ProblemAnswerVo problemAnswerVo : answers){
            securityProblem = new SecurityProblem();
            securityProblem.setAnswer(problemAnswerVo.getAnswer());
            securityProblem.setProblemId(problemAnswerVo.getProblemId());
            Assert.notNull(problemAnswerVo.getProblemId(),messageSourceAccessor.getMessage("error.payserver.saveySecurity.problem"));
            Assert.notNull(problemAnswerVo.getAnswer(),messageSourceAccessor.getMessage("error.payserver.saveySecurity.answer"));
            securityProblem.setPayAccountId(payAccount.getId());
            securityProblems.add(securityProblem);
        }
        return securityProblemDao.save(securityProblems);
    }

    @Override
    public List<SecurityProblemVo> findAccountSecurity(String userCode) {
        logger.debug("查询userCode的密保问题集合:"+userCode);
        PayAccount payAccount = payAccountDao.findByUserCode(userCode);
        Assert.notNull(payAccount,messageSourceAccessor.getMessage("error.payserver.saveySecurity.userCode"));
        List<SecurityProblemVo> securityProblemVoList = new ArrayList<>();
        List<SecurityProblem> list = securityProblemDao.findByPayAccountId(payAccount.getId());
        SecurityProblemVo securityProblemVo = null;
        for (SecurityProblem s:list) {
            securityProblemVo = SecurityProblemVo.domainToVo(s);
            securityProblemVoList.add(securityProblemVo);
        }
        return securityProblemVoList;
    }
}
