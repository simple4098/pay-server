package com.yql.biz.support.helper;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayOrderAccountDao;
import com.yql.biz.dao.IPayOrderAccountDetailDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayOrderAccount;
import com.yql.biz.model.PayOrderAccountDetail;
import com.yql.biz.vo.PayOrderAccountDetailVo;
import com.yql.biz.vo.PayOrderVo;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p> 支付订单helper </p>
 * @auther simple
 * data 2016/12/26 0026.
 */
@Component
@Transactional
public class PayOrderAccountHelper implements IPayOrderAccountHelper{
    private static final Logger logger = LoggerFactory.getLogger(PayOrderAccountHelper.class);
    @Resource
    private IPayOrderAccountDao payOrderAccountDao;
    @Resource
    private IPayOrderAccountDetailDao payOrderAccountDetailDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;

    @Override
    public void saveOrder(PayOrderAccount payOrderAccount) {
        PayOrderAccountDetail payOrderAccountDetail = PayOrderAccountDetailVo.toDomain(payOrderAccount);
        PayOrderAccount result = payOrderAccountDao.save(payOrderAccount);
        payOrderAccountDetail.setPayOrderAccountId(result.getId());
        logger.debug("订单详情save:"+ JSON.toJSONString(payOrderAccountDetail));
        PayOrderAccountDetail payOrderAccountDetail1 = payOrderAccountDetailDao.findByOrderNoAndPayStatus(payOrderAccountDetail.getOrderNo(),payOrderAccountDetail.getPayStatus());
        if (payOrderAccountDetail1==null){
            payOrderAccountDetailDao.save(payOrderAccountDetail);
        }
    }

    @Override
    public void saveOrderTransform(PayOrderAccount payOrderAccount, String userCode) {
        PayOrderAccount orderAccount = payOrderAccountDao.findByOrderNo(payOrderAccount.getOrderNo());
        if (orderAccount != null) {
            payOrderAccount.setId(orderAccount.getId());
            payOrderAccount.setVersion(orderAccount.getVersion());
        }
        PayAccount payAccount = payAccountServiceHelper.findOrCratePayAccount(userCode);
        payOrderAccount.setPayAccountId(payAccount.getId());
        saveOrder(payOrderAccount);
    }

    @Override
    public void paymentResponse(ResponseModel responseModel, PayOrderVo payOrderVo,PayOrderAccount payOrderAccount) {
        logger.debug(">>>>>>>>>>paymentResponse :"+JSON.toJSONString(responseModel));
        if (!responseModel.isSuccess()) {
            payOrderVo.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            payOrderAccount.setPayStatus(PayStatus.PAY_UNSUCCESS.getValue());
            payOrderAccount.setMsg(responseModel.getMessage());
            payOrderVo.setMsg(responseModel.getMessage());
            logger.debug("accounting 支付失败:"+ payOrderVo.getOrderNo()+" msg:"+responseModel.getMessage());
        }else {
            if (PayType.DRAW_MONEY.equals(payOrderAccount.getPayType())){
                payOrderVo.setPayStatus(PayStatus.HANDLING.getValue());
                payOrderAccount.setPayStatus(PayStatus.HANDLING.getValue());
            }else {
                payOrderVo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
                payOrderAccount.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
            }
            logger.debug("accounting 支付成功:"+ payOrderVo.getOrderNo());
        }
        saveOrderTransform(payOrderAccount,payOrderVo.getUserCode());
    }
}
