package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 支付订单helper </p>
 * @auther simple
 * data 2016/12/26 0026.
 */
@Component
public class PayOrderAccountHelper implements IPayOrderAccountHelper{
    private static final Logger logger = LoggerFactory.getLogger(PayOrderAccountHelper.class);
    @Resource
    private IPayOrderAccountDao payOrderAccountDao;
    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;

    @Override
    public void saveOrder(PayOrderAccount payOrderAccount) {
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        logger.debug("订单详情save:"+ JSON.toJSONString(payOrderAccountDetail));
        payOrderAccountDetailDao.save(payOrderAccountDetail);
    }
}
