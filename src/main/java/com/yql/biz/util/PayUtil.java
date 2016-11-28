package com.yql.biz.util;

import com.yql.biz.exception.MessageRuntimeException;
import org.apache.commons.lang.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>支付工具类</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public class PayUtil {

    private PayUtil(){}


    /**
     * MD5加密一个字符串
     * @param key 要加密的字符串
     * @throws Exception
     */
    public static String md5Key(String key) throws Exception {
        // 生成一个MD5加密计算摘要
        MessageDigest md5=MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(key.getBytes("utf-8"));
        return new String(digest);
    }

    /**
     * 生产num位数的数字+num位数的字母
     * @param num 多少位的字母，数字
     */
    public static String  randomCode(int num){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(RandomStringUtils.randomNumeric(num));
        stringBuffer.append(RandomStringUtils.randomAlphabetic(num));
        return  stringBuffer.toString();
    }

    /**
     *
     * @param num
     * @return
     */
    public static String  randomCodeNum(Integer num){
        if (null==num){
            throw new MessageRuntimeException("error.payserver.param.notnull");
        }
        return  RandomStringUtils.randomNumeric(num);
    }

    /**
     * 金额转化成分
     * @param totalPrice 支付金额
     */
    public static int multiply(BigDecimal totalPrice){
        return totalPrice.multiply(new BigDecimal(100)).intValue();
    }
   /* public static void main(String[] args) throws Exception {
        System.out.println(md5PassWord("ssdsd","123456","2323"));
        MessageDigest md5=MessageDigest.getInstance("MD5");
        md5.update(new String("ssdsd"+"123456"+"2323").getBytes());
        System.out.println(new BigInteger(1, md5.digest()).toString(16));
        System.out.println(RandomStringUtils.randomNumeric(0));
    }*/
}