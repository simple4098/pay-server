package com.yql.biz.util;

import com.yql.biz.exception.MessageRuntimeException;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;

/**
 * <p>支付工具类</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public class PayUtil {

    private PayUtil(){}




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
    public static int priceToCent(BigDecimal totalPrice){
        return totalPrice.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 10位 时间戳
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}