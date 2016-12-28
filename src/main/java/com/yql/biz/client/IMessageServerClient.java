package com.yql.biz.client;

import com.yql.core.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消息中心
 * @author simple
 */
@FeignClient(value = "message-server")
public interface IMessageServerClient {

    /**
     * 验证手机验证码是否正确
     * @param phone 电话号码
     * @param phoneCode 验证码
     * @param phoneCodeKey redis对应的业务key
     */
    @RequestMapping(value = "/check-phone-code", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    ResponseModel checkPhoneCode(@RequestParam(name = "phone") String phone,@RequestParam(name = "phoneCode") String phoneCode,
                                                  @RequestParam(name = "phoneCodeKey") String phoneCodeKey);

    /**
     *
     * @param messageType 消息类型
     * @param phone 电话号码
     * @param phoneCodeKey redis对应的业务key
     * @return
     */
    @RequestMapping(value = "/get-phone-auth-code", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    ResponseModel getPhoneAuthCode(@RequestParam(name = "messageType") String messageType,@RequestParam(name = "phone") String phone,@RequestParam(name = "phoneCodeKey") String phoneCodeKey);



}
