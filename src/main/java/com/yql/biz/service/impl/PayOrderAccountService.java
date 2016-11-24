package com.yql.biz.service.impl;

import com.alibaba.fastjson.JSON;
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
import com.yql.biz.vo.ResultPayOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>支付订单号</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
public class PayOrderAccountService implements IPayOrderAccountService {
    private static final Logger log = LoggerFactory.getLogger(PayOrderAccountService.class);
    @Resource
    private IPayOrderAccountDao payOrderAccountDao;

    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;

    @Override
    public ResultPayOrder order(PayOrderVo payOrderVo) {
        log.info("pay-server param:"+ JSON.toJSONString(payOrderVo));
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(payOrderVo.getUserCode());
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(payOrderVo.getOrderNo());
        PayOrderAccount payOrderAccount = PayOrderVo.toDomain(payOrderVo);
        payOrderAccount.setPayAccountId(payAccount.getId());
        IPayOrderAccountHelper order = payOrderVo.getPayType().createOrder();
        PayOrderVo orderVo = order.orderType(payOrderVo);
        payOrderAccount.setPayStatus(orderVo.getPayStatus());
        payOrderAccount.setPayOrder(orderVo.getPayOrder());
        payOrderAccount.setMsg(orderVo.getMsg());
        payOrderAccount.setPayNo(orderVo.getPayNo());
        payOrderAccount.setPayBankId(orderVo.getPayBankId());
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        if (orderAccount!=null){
            payOrderAccount.setId(orderAccount.getId());
            payOrderAccount.setVersion(orderAccount.getVersion());
        }
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        payOrderAccountDetailDao.save(payOrderAccountDetail);
        ResultPayOrder payOrder = PayOrderVo.toResultOrder(result);
        return  payOrder;
    }
}
