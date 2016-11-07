package com.yql.biz.controller;

import com.yql.biz.model.PayAccount;
import com.yql.biz.service.IPayAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/3 0003.
 */
@RestController()
@RequestMapping("/pay")
public class IndexController {
    @Resource
    private IPayAccountService payAccountService;
    @RequestMapping("/index")
    public String index(){
        PayAccount byUserCode = payAccountService.findByUserCode("123456");
        return "pay server hello";
    }

}
