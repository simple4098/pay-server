package com.yql.biz.util;

import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.security.Signature;

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
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 是否省略xm头声明信
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        //是否标准格式输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        StringWriter fw = new StringWriter();
        marshaller.marshal(t, fw);
        String xml = fw.toString();
        byte[] bytes = xml.getBytes();
        logger.debug("======xml format====="+xml);
        Signature sha1WithRSA = Signature.getInstance("SHA1WithRSA");
        String algorithm = sha1WithRSA.getAlgorithm();
        String message = new BASE64Encoder().encode(bytes);
        Param param = new Param(message,algorithm);
        logger.debug("======SHA1WithRSA 加密====="+algorithm);
        return   param;
    }

    public static boolean isSuccess(Response response){
        if (response!=null && response.getHead().getCode().equals("2000")){
            return true;
        }
        return  false;
    }

}
