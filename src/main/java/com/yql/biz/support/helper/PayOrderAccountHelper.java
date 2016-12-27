package com.yql.biz.support.helper;

import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> 支付订单helper </p>
 * @auther simple
 * data 2016/12/26 0026.
 */
@Component
public class PayOrderAccountHelper implements IPayOrderAccountHelper{

    @Resource
    private IPayOrderAccountDao payOrderAccountDao;
    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;

    @Override
    public void saveOrder(PayOrderAccount payOrderAccount) {
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        payOrderAccountDetailDao.save(payOrderAccountDetail);
    }
}
