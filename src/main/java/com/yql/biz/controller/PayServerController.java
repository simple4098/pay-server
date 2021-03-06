package com.yql.biz.controller;

import com.yql.biz.constraint.OrderNo;
import com.yql.biz.constraint.UserCode;
import com.yql.biz.service.*;
import com.yql.biz.vo.*;
import com.yql.biz.model.PayAccount;
import com.yql.core.web.ResponseModel;
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
    @Resource
    private IPayOrderAccountService payOrderAccountService;
    @Resource
    private IFyBankCityCodeService fyBankCityCodeService;



    /**
     * 开关小额支付并且小额支付金额
     * @return
     */
    @RequestMapping("/open-close/small-money-pay")
    public ResponseModel openClose(@Validated  PayAccountVo payAccount){
        payAccountService.updatePayAccountSmallMoney(payAccount);
        return ResponseModel.SUCCESS();
    }

    @RequestMapping("/real-name-auth")
    public ResponseModel realNameAuth(@Validated PayAccount payAccount){
        payAccountService.updateRealNameAuth(payAccount);
        return ResponseModel.SUCCESS();
    }
    /**
     * 支付系统问题集合
     */
    @RequestMapping("/problem-list")
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
     * 设置支付密码
     */
    @RequestMapping(value = "/set/pay-password",method = RequestMethod.POST)
    public ResponseModel setPayPassword(@Validated  PayAccountVo payAccount){
        payAccountService.setPayPassword(payAccount);
        return ResponseModel.SUCCESS();
    }

    /**
     * 判断是否支付过密码
     */
    @RequestMapping(value = "/get/pay-password",method = RequestMethod.GET)
    public ResponseModel getPayPassword(@UserCode  String userCode){
        ResultPayPassword payPassword = payAccountService.isPayPassword(userCode);
        return ResponseModel.SUCCESS(payPassword);
    }
    /**
     * 更新支付密码
     */
    @RequestMapping(value = "/update/pay-password",method = RequestMethod.POST)
    public ResponseModel updatePayPassword(@Validated  PayAccountVo payAccount){
        payAccountService.updatePayPassword(payAccount);
        return ResponseModel.SUCCESS();
    }
    /**
     * 验证支付密码是否成功
     */
    @RequestMapping(value = "/pay-password",method = RequestMethod.POST)
    public ResponseModel payPassword(@Validated PayAccountVo payAccount){
        payAccountService.validatePassword(payAccount);
        return ResponseModel.SUCCESS();
    }

    /**
     * 重置支付密码
     */
    @RequestMapping(value = "/reset-pay-password",method = RequestMethod.GET)
    public ResponseModel payPassword(@UserCode String userCode){
        payAccountService.resetDefaultPayPassword(userCode);
        return ResponseModel.SUCCESS("error.payserver.resetPayPassword");
    }

    /**
     * <p>找回支付密码发送验证码,支付密码只发送到登录电话号码上</p>
     */
    @RequestMapping(value = "/reset/pay-password",method = RequestMethod.POST)
    public ResponseModel senMessage(@Validated ResetPayPasswordVo resetPayPasswordVo){
        payAccountService.resetPayPassword(resetPayPasswordVo);
        return ResponseModel.SUCCESS();
    }

    /**
     * 添加支付银行卡
     */
    @RequestMapping(value = "/set/bank-card" ,method = RequestMethod.POST)
    public ResponseModel setBankCard(@Validated PayBankVo payBankVo){
        ResultBangBank resultBangBank = payBankService.savePayBank(payBankVo);
        return ResponseModel.SUCCESS(resultBangBank);
    }
    /**
     * 删除支付银行卡
     */
    @RequestMapping(value = "/del/bank-card" ,method = RequestMethod.POST)
    public ResponseModel delBankCard(@Validated DelBankCardVo delBankCardVo){
         payBankService.delBangBank(delBankCardVo);
        return ResponseModel.SUCCESS();
    }
    /**
     * 获取用户银行卡列表信息
     */
    @RequestMapping("/get/bank-card")
    public ResponseModel obtBankCard(@UserCode  String userCode){
        List<PayBankVo> list = payBankService.findByUserCode(userCode);
        return ResponseModel.SUCCESS(list);
    }

    /**
     * 获取用户银行卡列表信息
     */
    @RequestMapping("/get/bank-num")
    public ResponseModel obtBankNum(@UserCode  String userCode){
        PayBankNumVo PayBankNumVo = payBankService.findBankNumByUserCode(userCode);
        return ResponseModel.SUCCESS(PayBankNumVo);
    }

    /**
     * 查询订单状态
     * @param orderNo 订单号
     */
    @RequestMapping("/query/order")
    public ResponseModel queryOrder(@OrderNo String orderNo){
        ResultPayOrder resultPayOrder =   payOrderAccountService.findOrderInfo(orderNo);
        return ResponseModel.SUCCESS(resultPayOrder);
    }

    /**
     * 查询所有的城市列表
     */
    @RequestMapping("/query/all-city")
    public ResponseModel queryAllCityCode(){
        List<FyBankCityCodeVo> allCityCode = fyBankCityCodeService.findAllCityCode();
        return ResponseModel.SUCCESS(allCityCode);
    }
}
