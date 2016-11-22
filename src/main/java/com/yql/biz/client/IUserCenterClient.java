package com.yql.biz.client;

import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.biz.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户中心client
 * @author simple
 */
@FeignClient(value = "user-center",url = "${pay-server.userHost}",fallback = UserCenterHystrix.class)
public interface IUserCenterClient {

    /**
     * 获取用户的信息
     * @param userCode 用户编码
     */
    @RequestMapping(value = "/find-user-basic-info", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    ResponseModel<UserBasicInfoVo> getBaseUserInfo(@RequestParam(name = "userCode") String userCode);


}
