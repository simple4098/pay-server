package com.yql.biz.controller;

import com.yql.biz.constraint.UserCode;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>支付账号相关信息 控制器</p>
 * <p>包含：密保设置   密码修改  小额支付设置 ...</p>
 * Created simple
 */
@RestController
@RequestMapping("/account")
@Validated
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
    public ResponseModel index(@Validated  PayAccount payAccount){
         PayAccount payAccount1 = payAccountService.savePayAccount(payAccount);
         return ResponseModel.SUCCESS(payAccount1);
    }

    /**
     * 开关小额支付并且小额支付金额
     * @return
     */
    @RequestMapping("/open_close/samll_money_pay")
    public ResponseModel openClose(@Validated  PayAccount payAccount){
        payAccountService.updatePayAccountSamllMoney(payAccount);
        return ResponseModel.SUCCESS();
    }

    @RequestMapping("/real_name_auth")
    public ResponseModel realNameAuth(@Validated PayAccount payAccount){
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
    public ResponseModel paySecurity(@NotNull(message = "{com.yql.validation.constraints.json.message}") String json){
        payProblemService.saveySecurity(json);
        return ResponseModel.SUCCESS();
    }

    /**
     * 获取用户密保问题
     * @param userCode 用户code
     */
    @RequestMapping("/get/security")
    @ResponseBody
    public ResponseModel accountSecurity(@UserCode  String userCode){
        List<SecurityProblemVo> list = payProblemService.findAccountSecurity(userCode);
        return ResponseModel.SUCCESS(list);
    }

    /**
     * 验证用户的密保是否正确
     * @param json json 数据字符串
     * @return
     */
    @RequestMapping(value = "/validate/security",method = RequestMethod.POST)
    public ResponseModel validate(@NotNull(message = "{com.yql.validation.constraints.json.message}") String json){
        payProblemService.validatesSecurity(json);
        return ResponseModel.SUCCESS();
    }

    /**
     * 更新支付密码
     */
    @RequestMapping(value = "/update/pay_password",method = RequestMethod.POST)
    public ResponseModel updatePayPassword(@Validated  PayAccountVo payAccount){
        payAccountService.updatePayPassword(payAccount);
        return ResponseModel.SUCCESS();
    }
    /**
     * 验证支付密码是否成功
     */
    @RequestMapping(value = "/pay_password",method = RequestMethod.POST)
    public ResponseModel payPassword(@Validated PayAccount payAccount){
        payAccountService.validatePassword(payAccount);
        return ResponseModel.SUCCESS();
    }

    /**
     * 添加支付银行卡
     */
    @RequestMapping(value = "/set/bank_card" ,method = RequestMethod.POST)
    public ResponseModel setBankCard(@Validated PayBankVo payBankVo){
        payBankService.savePayBanke(payBankVo);
        return ResponseModel.SUCCESS();
    }
    /**
     * 删除支付银行卡
     */
    @RequestMapping(value = "/del/bank_card" ,method = RequestMethod.POST)
    public ResponseModel delBankCard(@NotNull(message = "{com.yql.validation.constraints.txCode.notnull}") String txCode,
                                     @NotNull(message = "{com.yql.validation.constraints.payAccountId.notnull}") Integer payAccountId){
        payBankService.delBangBank(txCode,payAccountId);
        return ResponseModel.SUCCESS();
    }
    /**
     * 获取用户银行卡列表信息
     */
    @RequestMapping(value = "/get/bank_card" ,method = RequestMethod.POST)
    public ResponseModel obtBankCard(@UserCode  String userCode){
        List<PayBankVo> list = payBankService.findByUserCode(userCode);
        return ResponseModel.SUCCESS(list);
    }
}
