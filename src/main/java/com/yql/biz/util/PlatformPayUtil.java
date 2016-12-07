package com.yql.biz.util;

import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.security.Signature;
import java.util.*;

/**
 * <p> 支付工具类 </p>
 * @auther simple
 * data 2016/11/16 0016.
 */
public class PlatformPayUtil<T extends DjPay> {
    private static final Logger logger = LoggerFactory.getLogger(PlatformPayUtil.class);
    private PlatformPayUtil(){}

    /**
     * 泛型对象转换成xml字符串
     * @param t 泛型类的实例
     * @param <T> 泛型类
     * @throws JAXBException
     */
    public static <T> Param payRequest(T t) throws Exception {
        /*JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 是否省略xm头声明信
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        //是否标准格式输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        StringWriter fw = new StringWriter();
        marshaller.marshal(t, fw);*/
        String xml = payRequestXml(t);
        byte[] bytes = xml.getBytes();
        logger.debug("======xml format====="+xml);
        Signature sha1WithRSA = Signature.getInstance("SHA1WithRSA");
        String algorithm = sha1WithRSA.getAlgorithm();
        String message = new BASE64Encoder().encode(bytes);
        Param param = new Param(message,algorithm);
        logger.debug("======SHA1WithRSA 加密====="+algorithm);
        return   param;
    }

    public static <T> String payRequestXml(T t)throws Exception{
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 是否省略xm头声明信
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        //是否标准格式输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.FALSE);
        StringWriter fw = new StringWriter();
        marshaller.marshal(t, fw);
        return fw.toString();
    }

    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }


    /**
     * 快捷支付是否成功
     * @param response
     * @return
     */
    public static boolean isSuccess(Response response){
        if (response!=null && response.getHead().getCode().equals("2000")){
            return true;
        }
        return  false;
    }

    /**
     * 比较两个字符串ASCII 字母排序
     * @param pre
     * @param next
     * @return
     */
    private static boolean isMoreThan(String pre, String next){
        if(null == pre || null == next || "".equals(pre) || "".equals(next)){
            logger.error("字符串比较数据不能为空！");
            return false;
        }
        char[] c_pre = pre.toCharArray();
        char[] c_next = next.toCharArray();
        int minSize = Math.min(c_pre.length, c_next.length);
        for (int i = 0; i < minSize; i++) {
            if((int)c_pre[i] > (int)c_next[i]){
                return true;
            }else if((int)c_pre[i] < (int)c_next[i]){
                return false;
            }
        }
        if(c_pre.length > c_next.length){
            return true;
        }
        return false;
    }

    /**
     *
     * @param t 微信相关对象（微信下预付订单对象、APP 请求支付对象）
     * @return 把一个对象的 属性-值 以key-value的形式存起来
     */
    public static <T extends DjPay> Map<String,Object> obtObjParm( T t) {
        Method[] methods = t.getClass().getMethods();
        SortedMap<String,Object> param = new TreeMap();
        //Map<String,Object> param = new HashMap<>();
        for (Method m:methods){
            XmlElement annotation = m.getAnnotation(XmlElement.class);
            if (annotation!=null){
                Object invoke = null;
                try {
                    invoke = m.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String name = annotation.name();
                if (!StringUtils.isEmpty(invoke)){
                    param.put(name,invoke);
                }
            }
        }
        return param;
    }

    /**
     *
     * @param strings 要排序的字符串set
     */
    public static String[] getUrlParam(Set<String> strings){
        String[] keys = new String[strings.size()];
        int k = 0;
        for (String v:strings ) {
            keys[k] = v;
            k++;
        }
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - i -1; j++) {
                String pre = keys[j];
                String next = keys[j + 1];
                if(isMoreThan(pre, next)){
                    String temp = pre;
                    keys[j] = next;
                    keys[j+1] = temp;
                }
            }
        }
        return keys;
    }

    /**
     * map的key-value形式转化成 k=v&k=v ...  并按照ASCII排序
     * @param param map k-v 转化成 k=v&k=v ...
     * @return
     */
    public static String concatStr(Map<String, Object> param) {
        Set<String> strings = param.keySet();
        String[] urlParam = PlatformPayUtil.getUrlParam(strings);
        List<String> strings1 = Arrays.asList(urlParam);
        StringBuffer sb = new StringBuffer();
        for (String s: strings1) {
            sb.append(s).append("=").append(param.get(s)).append("&");
        }
        if(StringUtils.hasText(sb.toString())){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    public static String sortParm(Map parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        return  sb.toString();
    }
}
