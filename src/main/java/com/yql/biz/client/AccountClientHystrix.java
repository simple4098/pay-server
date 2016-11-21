package com.yql.biz.client;

import com.yql.biz.vo.AccountVo;
import com.yql.biz.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 账户中心客户端
 * @author simple
 */
@Component
public class AccountClientHystrix  implements IAccountClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountClientHystrix.class);

    @Override
    public ResponseModel<AccountVo> getAccount(String userCode) {
        logger.debug("==========================bangBank支付断路器==========================");
        return null;
    }
}
