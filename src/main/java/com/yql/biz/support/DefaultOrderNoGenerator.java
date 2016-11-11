package com.yql.biz.support;

import com.yql.biz.conf.ApplicationConf;
import com.yql.biz.enums.PayType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;


@Component("orderNoGenerator")
public class DefaultOrderNoGenerator implements OrderNoGenerator {
    private LocalDate releaseMonth = LocalDate.of(2016, 12, 1);
    @Resource
    private ApplicationConf constants;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Override
    public long generate(PayType orderType) {
        int ordinal = orderType.ordinal();
        ordinal += 11;//+10以补齐两位,+1以使从1开始而非0
        LocalDateTime now = LocalDateTime.now();
        int months = Period.between(releaseMonth, now.toLocalDate()).getMonths();
        months += 11;
        int dayOfMonth = now.getDayOfMonth();
        long seconds = now.getLong(ChronoField.SECOND_OF_DAY);
        String no = String.valueOf(months) + ordinal + new DecimalFormat("00").format(dayOfMonth) + new DecimalFormat("00000").format(seconds)
                + redisTemplate.boundSetOps(constants.getOrderNumKey()).pop(); //上线后的月份递增量(+11)[2位]+订单类型(+11)[2位]+天数[2位]+秒数[5位]+随机数[4位]=订单号[15位]
        return Long.valueOf(no);
    }
}
