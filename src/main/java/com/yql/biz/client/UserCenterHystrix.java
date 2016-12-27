package com.yql.biz.client;

import com.yql.biz.vo.UserBasicInfoVo;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 断路器实现类
 * @author simple
 */
@Component
public class UserCenterHystrix implements IUserCenterClient {
    private static  final Logger logger = LoggerFactory.getLogger(UserCenterHystrix.class);


    @Override
    public ResponseModel<UserBasicInfoVo> getBaseUserInfo(@RequestParam(name = "userCode") String userCode) {
        logger.debug("==========================getBaseUserInfo断路器==========================");
        return null;
    }

    @Override
    public ResponseModel<UserBasicInfoVo> checkUserExist(@RequestParam(name = "userCode") String userCode) {
        return null;
    }


}
