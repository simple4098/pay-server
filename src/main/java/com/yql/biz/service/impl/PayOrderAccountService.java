package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.service.IPayOrderAccountService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.IPayOrderAccountHelper;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import com.yql.biz.vo.PayOrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>支付订单号</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
public class PayOrderAccountService implements IPayOrderAccountService {

    @Resource
    private IPayOrderAccountDao payOrderAccountDao;

    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;

    @Override
    public PayOrderVo order(PayOrderVo payOrderVo) {
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(payOrderVo.getUserCode());
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(payOrderVo.getOrderNo());
        PayOrderAccount payOrderAccount = PayOrderVo.toDomain(payOrderVo);
        payOrderAccount.setPayAccountId(payAccount.getId());
        // TODO: 2016/11/10 0010 调用第三方支付
        IPayOrderAccountHelper order = payOrderVo.getPayType().createOrder();
        PayOrderVo orderVo = order.orderType(payOrderVo);
        payOrderAccount.setPayStatus(orderVo.getPayStatus());
        payOrderAccount.setPayOrder(orderVo.getPayOrder());
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        if (orderAccount==null){
            payOrderAccount.setPayNo(orderVo.getPayNo());
        }else {
            payOrderAccount.setId(orderAccount.getId());
            payOrderAccount.setVersion(orderAccount.getVersion());
        }
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        payOrderAccountDetailDao.save(payOrderAccountDetail);
        PayOrderVo vo = PayOrderVo.domainToVo(result);
        return  vo;
    }
}
