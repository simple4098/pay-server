package com.yql.biz.client;

import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.core.web.ResponseModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户中心client
 * @author simple
 */
@FeignClient(value = "user-center",fallback = UserCenterHystrix.class)
public interface IUserCenterClient {

    /**
     * 获取用户的信息
     * @param userCode 用户编码
     */
    @RequestMapping(value = "/user/find-user-basic-info", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    ResponseModel<UserBasicInfoVo> getBaseUserInfo(@RequestParam(name = "userCode") String userCode);

    /**
     * <p>账户中心 查询用户是否存在；如果存在 正常返回</p>
     * @param userCode 用户唯一编码
     */
    @RequestMapping(value = "/user/check-user-exist",method = RequestMethod.GET)
    ResponseModel<UserBasicInfoVo> checkUserExist(@RequestParam(name = "userCode") String userCode);


}
