package com.yql.biz.support;

import com.yql.biz.enums.BankCodeType;
import com.yql.biz.enums.PayType;
import com.yql.biz.model.PayBank;
import com.yql.biz.util.PayUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Component("orderNoGenerator")
public class DefaultOrderNoGenerator implements OrderNoGenerator {


    @Override
    public long generate(PayType orderType) {
        StringBuffer payNo = new StringBuffer();
        String format = DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmssSSS");
        int ordinal = orderType.ordinal();
        String s = PayUtil.randomCodeNum(2);
        payNo.append(format).append(s).append(ordinal);
        return Long.valueOf(payNo.toString());
    }

    @Override
    public String generateBankCode(PayBank payBank, BankCodeType bankCodeType) {
        StringBuffer txCode = new StringBuffer(bankCodeType.name());
        String s = PayUtil.randomCode(3);
        txCode.append(payBank.getUserCode()).append(s);
        return txCode.toString();
    }


}
