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
    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SIMPLE_TIME = "yyyy-MM-dd";

    public static Date getFormatDate(String pattern){
        String pa = pattern==null?FORMAT_SIMPLE_TIME:pattern;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pa);
        String format = getStringFormatTime(pattern);
        try {
            return dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringFormatTime(String pattern){
        String pa = pattern==null?FORMAT_SIMPLE_TIME:pattern;
        Date nowDate = new Date();
        return DateFormatUtils.format(nowDate, pa);
    }

    /**
     * 前一天的开始时间
     * @return
     */
    public static Date getStartBeforeDay(){
        Date formatDate = getFormatDate(FORMAT_SIMPLE_TIME);
        return DateUtils.addDays(formatDate, -1);
    }

    /**
     * 前一天的结束时间
     * @return
     */
    public static Date getEndTime(){
        Date formatDate = getFormatDate(FORMAT_SIMPLE_TIME);
        return DateUtils.addSeconds(formatDate, -1);
    }

    /**
     * 查询微信预付单的开始时间
     */
    public static Date getWxStartTime(){
        Date formatDate = getFormatDate(FORMAT_TIME);
        return DateUtils.addHours(formatDate, -3);
    }
    /**
     * 查询微信预付单的开始时间
     */
    public static Date getWxEndTime(){
        Date formatDate = getFormatDate(FORMAT_TIME);
        return DateUtils.addHours(formatDate, -1);
    }

    public static Date formatDate(String date,String pattern){
        String pa = pattern==null?FORMAT_TIME:pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(pa);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
