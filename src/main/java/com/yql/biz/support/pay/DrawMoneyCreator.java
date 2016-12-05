package com.yql.biz.support.pay;

import com.alibaba.fastjson.JSON;
import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.enums.PayType;
import com.yql.biz.enums.pay.PayStatus;
import com.yql.biz.model.PayAccount;
import com.yql.biz.support.OrderNoGenerator;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.support.helper.SendMessageHelper;
import com.yql.biz.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/12/1 0001.
 */
@Service
@Transactional
public class DrawMoneyCreator implements IPayOrderCreator{
    private static  final Logger log = LoggerFactory.getLogger(DrawMoneyCreator.class);
    @Resource
    private OrderNoGenerator orderNoGenerator;
    @Resource
    private SendMessageHelper sendMessageHelper;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;
    @Resource
    private IPayAccountDao payAccountDao;

    @Override
    public PayOrderVo transform(PayOrderVo payOrderVo) {
        log.debug("提现申请json:"+JSON.toJSONString(payOrderVo));
        //支付验证支付密码
        PayAccount payAccount = payAccountDao.findByUserCode(payOrderVo.getUserCode());
        payPasswordSecurityHelper.validateOldPassword(payOrderVo.getPayPassword(),payAccount);
        payAccountServiceHelper.validateDrawMoney(payOrderVo);
        String payNo = orderNoGenerator.generate(payOrderVo.getPayType());
        payOrderVo.setPayNo(payNo);
        payOrderVo.setPayStatus(PayStatus.DRAW_MONEY_HANDLING.getValue());
        sendMessageHelper.sendDrawMoney(payOrderVo);
        return payOrderVo;
    }

    @Override
    public boolean supports(PayOrderVo payOrderVo) {
        return PayType.DRAW_MONEY.equals(payOrderVo.getPayType());
    }
}
