package com.yql.biz.util;

import com.yql.core.exception.MessageRuntimeException;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>支付工具类</p>
 * creator simple
 * data 2016/11/7 0007.
 */
public class PayUtil {
   private static  final Logger log = LoggerFactory.getLogger(PayUtil.class);
    private PayUtil(){}




    /**
     * 生产num位随机字符串
     * @param num 多少位的字母，数字
     */
    public static String  randomCode(int num){
        String randomCode = RandomStringUtils.randomAlphanumeric(num);
        return  randomCode;
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
     * 元转化成分
     * @param totalPrice 支付金额
     */
    public static int priceToCent(BigDecimal totalPrice){
        return totalPrice.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 分转化成元
     * @param cent 分
     * @return
     */
    public static BigDecimal centToPrice(Integer cent){
        return  new BigDecimal(cent).divide(new BigDecimal(100));
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
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static Date dataFormat(String timeEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.parse(timeEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String toXml(HttpServletRequest request){
        String inputLine;
        String notityXml = "";
        try{
            BufferedReader reader = request.getReader();
            while ((inputLine =reader.readLine()) != null) {
                notityXml += inputLine;
            }
            return notityXml;
        }catch (Exception e){
            log.error("wx 异步通知xml:",e);
        }
        return null;
    }

    public static boolean validatedIp(String ip){
        if (StringUtils.hasText(ip)){
            boolean matches = ip.matches("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
            return  matches;
        }
        return false;
    }
}