package com.yql.biz.controller;

import com.yql.biz.service.IPayBankService;
import com.yql.biz.vo.PayAccountVo;
import com.yql.biz.vo.PayBankVo;
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
    @Resource
    private IPayBankService payBankService;

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
           return ResponseModel.ERROR(e.getMessage());
        }
    }

    /**
     * 开关小额支付并且小额支付金额/open_close/samll_money_pay
     * @return
     */
    @RequestMapping("/open_close/samll_money_pay")
    public ResponseModel openClose(PayAccount payAccount){
        payAccountService.updatePayAccountSamllMoney(payAccount);
        return ResponseModel.SUCCESS();
    }

    @RequestMapping("/real_name_auth")
    public ResponseModel realNameAuth(PayAccount payAccount){
        payAccountService.updateRealNameAuth(payAccount);
        return ResponseModel.SUCCESS();
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


    /**
     * 更新支付密码
     */
    @RequestMapping(value = "/update/pay_password",method = RequestMethod.POST)
    public ResponseModel updatePayPassword(PayAccountVo payAccount){
        payAccountService.updatePayPassword(payAccount);
        return ResponseModel.SUCCESS();
    }
    /**
     * 严重支付密码是否成功
     */
    @RequestMapping(value = "/pay_password",method = RequestMethod.POST)
    public ResponseModel payPassword(PayAccount payAccount){
        payAccountService.validatePassword(payAccount);
        return ResponseModel.SUCCESS();
    }

    /**
     * 添加支付银行卡
     */
    @RequestMapping(value = "/set/bank_card" ,method = RequestMethod.POST)
    public ResponseModel setBankCard(PayBankVo payBankVo){
        payBankService.savePayBanke(payBankVo);
        return ResponseModel.SUCCESS();
    }
}
