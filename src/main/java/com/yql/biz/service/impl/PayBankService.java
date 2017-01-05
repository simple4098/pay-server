package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayAccountDao;
import com.yql.biz.dao.IPayBankDao;
import com.yql.biz.model.PayAccount;
import com.yql.biz.model.PayBank;
import com.yql.biz.service.IPayBankService;
import com.yql.biz.support.helper.IPayAccountServiceHelper;
import com.yql.biz.support.helper.PayPasswordSecurityHelper;
import com.yql.biz.vo.*;
import com.yql.core.exception.MessageRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>支付银行卡Service</p>
 * creator simple
 * data 2016/11/10 0010.
 */
@Service
@Transactional
public class PayBankService implements IPayBankService {
    @Resource
    private IPayBankDao payBankDao;
    @Resource
    private IPayAccountServiceHelper payAccountServiceHelper;
    @Resource
    private PayPasswordSecurityHelper payPasswordSecurityHelper;
    @Resource
    private IPayAccountDao payAccountDao;


    @Override
    public ResultBangBank savePayBank(PayBankVo payBankVo) {
        PayBank newPayBak = new PayBank();
        ResultBangBank resultBangBank = payAccountServiceHelper.crateQuickBangBankParam(payBankVo,newPayBak);
        payBankDao.save(newPayBak);
        return resultBangBank;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayBankVo> findByUserCode(String userCode) {
        List<PayBank> list = payBankDao.findByUserCodeAndDeleted(userCode,false);
        return PayBankVo.domainToVoList(list);
    }

    @Override
    public ResultUnBangBank delBangBank(DelBankCardVo delBankCard) {
        PayAccount one = payAccountDao.findOne(delBankCard.getPayAccountId());
        //移除银行卡验证密码
        payPasswordSecurityHelper.validateOldPassword(delBankCard.getPayPassword(),one);
        ResultUnBangBank resultUnBangBank = new ResultUnBangBank();
        PayBank payBank = payBankDao.findByPayAccountIdAndTxCodeAndDeleted(delBankCard.getPayAccountId(),delBankCard.getTxCode() ,false);
        if (payBank == null) throw new MessageRuntimeException("error.payserver.payServer.bank.isnull");
        payBank.setDeleted(true);
        payBankDao.save(payBank);
        return resultUnBangBank;
    }

    @Override
    public PayBankNumVo findBankNumByUserCode(String userCode) {
        long count = payBankDao.countByUserCodeAndDeleted(userCode,false);
        return new PayBankNumVo(userCode,count);
    }
}
