package com.yql.biz.util;

import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.DjPay;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
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
    public static <T> String payRequest(T t) throws Exception {
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
        logger.debug("======SHA1WithRSA 加密====="+algorithm);
        return   new BASE64Encoder().encode(xml.getBytes());

    }

    public static void main(String[] args) {
        try {
            Request<BangBody> request1 = new Request<>();
            Head head = new Head() ;
            head.setInstitutionID("sdsdsdsd111");
            head.setTxCode("1245450");
            request1.setHead(head);
            BangBody bangBody = new BangBody();
            bangBody.setAccountName("张琳");
            request1.setBody(bangBody);
            String s = PlatformPayUtil.payRequest(request1);
            System.out.println("==================="+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
