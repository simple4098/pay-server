package com.yql.biz.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> 支付时间处理工具类 </p>
 * @auther simple
 * data 2016/12/2 0002.
 */
public class PayDateUtil {
    private static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_SIMPLE_TIME = "yyyy-MM-dd";

    public static Date getFormatDate(){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_SIMPLE_TIME);
        String format = DateFormatUtils.format(nowDate, FORMAT_SIMPLE_TIME);
        //DateFormat dateFormat = DateFormat.getDateTimeInstance();
        try {
            return dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 前一天的开始时间
     * @return
     */
    public static Date getStartTime(){
        Date formatDate = getFormatDate();
        return DateUtils.addDays(formatDate, -1);
    }

    /**
     * 前一天的结束时间
     * @return
     */
    public static Date getEndTime(){
        Date formatDate = getFormatDate();
        return DateUtils.addSeconds(formatDate, -1);
    }

    public static void main(String[] args) {
        System.out.println(getFormatDate());
        System.out.println(getStartTime());
        System.out.println(getEndTime());
    }
}
