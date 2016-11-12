package com.yql.biz.support;

import com.yql.biz.enums.PayType;
import com.yql.biz.util.PayUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Component("orderNoGenerator")
public class DefaultOrderNoGenerator implements OrderNoGenerator {


    @Override
    public long generate(PayType orderType) {
        String format = DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmssSSS");
        int ordinal = orderType.ordinal();
        String s = PayUtil.randomCodeNum(2);
        String payNo = format+s + ordinal;
        Long aLong = Long.getLong(payNo);
        return aLong;
    }
}
