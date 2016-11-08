package com.yql.biz.controller;

import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import com.yql.biz.web.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
@RestController()
@RequestMapping("/pay-server")
public class PayServerController {
    @Resource
    private IPayAccountService payAccountService;

    @RequestMapping("/account")
    @ResponseBody
    public ResponseModel index(PayAccount payAccount){
        /*try{
            PayAccount payAccount1 = payAccountService.savePayAccount(payAccount);
            return ResponseModel.SUCCESS(payAccount1);
        }catch (Exception e){
           return ResponseModel.SUCCESS();
        }*/
        return ResponseModel.SUCCESS();
    }

}
