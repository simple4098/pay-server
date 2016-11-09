package com.yql.biz.controller;

import com.yql.biz.vo.PayProblemDto;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.SecurityProblem;
import com.yql.biz.service.IPayAccountService;
import com.yql.biz.service.IPayProblemService;
import com.yql.biz.vo.SecurityProblemVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
@RestController
@RequestMapping("/account")
public class PayServerController {
    @Resource
    private IPayAccountService payAccountService;
    @Resource
    private IPayProblemService payProblemService;

    /**
     * 初始化用户的支付信息
     * @param payAccount 账户信息  userCode ； 支付密码 payPassword
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public ResponseModel index(PayAccount payAccount){
        try{
            PayAccount payAccount1 = payAccountService.savePayAccount(payAccount);
            return ResponseModel.SUCCESS(payAccount1);
        }catch (Exception e){
           return ResponseModel.ERROR(500,e.getMessage());
        }
    }

    /**
     * 支付系统问题集合
     */
    @RequestMapping("/problem_list")
    public ResponseModel securityProblem(){
        List<PayProblemDto> payProblemList = payProblemService.findPayProblemList();
        return ResponseModel.SUCCESS(payProblemList);
    }

    /**
     * 支付密保问题设置
     */
    @RequestMapping(value = "/set/security",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel paySecurity(String json){
        List<SecurityProblem> securityProblems = payProblemService.saveySecurity(json);
        return ResponseModel.SUCCESS(securityProblems);
    }
    @RequestMapping("/get/security")
    @ResponseBody
    public ResponseModel accountSecurity(String userCode){
        List<SecurityProblemVo> list = payProblemService.findAccountSecurity(userCode);
        return ResponseModel.SUCCESS(list);
    }

}