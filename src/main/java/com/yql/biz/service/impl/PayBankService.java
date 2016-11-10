package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.service.IPayBankService;
import com.yql.biz.vo.PayBankVo;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>支付银行卡Service</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
public class PayBankService implements IPayBankService {
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private IPayAccountDao payAccountDao;
    @Resource
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public PayBank savePayBanke(PayBankVo payBankVo) {
        Assert.notNull(payBankVo.getUserCode(),messageSourceAccessor.getMessage("error.payserver.param.usercode"));
        if (StringUtils.isEmpty(payBankVo.getBankCard())){
            throw new MessageRuntimeException("error.payserver.paybankCard.empty");
        }
        if (StringUtils.isEmpty(payBankVo.getCardholder())){
            throw new MessageRuntimeException("error.payserver.paybankCard.cardholder");
        }
        PayAccount payAccount = payAccountDao.findByUserCode(payBankVo.getUserCode());
        if (payAccount.isRealNameAuth()){
            PayBank payBank = payBankDao.findByPayAccountIdAndBankCard(payAccount.getId(),payBankVo.getBankCard());
            if (payBank!=null){
                throw new MessageRuntimeException("error.payserver.paybankCard.repeat");
            }
            PayBank newPayBak = PayBankVo.voToDomain(payBankVo, payAccount);
            List<PayBank> list = payBankDao.findByPayAccountIdOrderBySort(payAccount.getId());
            if (!CollectionUtils.isEmpty(list)){
                newPayBak.setSort(list.size());
            }
            //// TODO: 2016/11/10 0010 调用第三方借口
            return payBankDao.save(newPayBak);
        }else {
            throw new MessageRuntimeException("error.payserver.isRealNameAuth");
        }

    }
}
