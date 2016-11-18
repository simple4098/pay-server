package com.yql.biz.support;

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
    public String txSNBinding(PayBank payBank) {
        StringBuffer txSNBingding = new StringBuffer();
        String s = PayUtil.randomCode(3);
        txSNBingding.append(payBank.getUserCode()).append(s);
        return txSNBingding.toString();
    }

    @Override
    public String txCode(PayBank payBank) {
        StringBuffer txCode = new StringBuffer();
        String s = PayUtil.randomCode(3);
        txCode.append(payBank.getUserCode()).append(s);
        return txCode.toString();
    }
}
