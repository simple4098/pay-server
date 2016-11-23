package com.yql.biz;

import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.request.*;
import com.yql.biz.vo.pay.response.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;

/*@RunWith(SpringRunner.class)*/
public class PayServerUtilTests {
    private static  final Logger logger = LoggerFactory.getLogger(PayServerUtilTests.class);

    //绑定银行卡(发生验证短信)
    @Test
    public void  testUtil() throws Exception {
        Request<BangBody> request1 = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request1.setHead(head);
        BangBody bangBody = new BangBody();
        bangBody.setAccountName("张琳");
        bangBody.setAccountName("中国人民银行");
        bangBody.setAccountNumber("62222222255454454");
        bangBody.setBankId("784");
        bangBody.setCardType(10);
        bangBody.setPhoneNumber("18123280669");
        bangBody.setTxSNBinding("777777777777777");
        bangBody.setIdentificationType(0);
        bangBody.setIdentificationNumber("510922xxxxxxxxxx");
        bangBody.setValidDate(1230);
        bangBody.setcVN2(568);
        request1.setBody(bangBody);
        Param s = PlatformPayUtil.payRequest(request1);
        logger.debug("=========request json :"+s.getMessage());
        Response response = new Response();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setCode("2000");
        responseHead.setMessage("成功");
        response.setHead(responseHead);
        s = PlatformPayUtil.payRequest(response);
        logger.debug("=========response json :"+s.getMessage());
    }

    //短信验证绑定
    @Test
    public void  testMessageBang() throws Exception {
        Request<BangMessageValidateBody> request = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request.setHead(head);
        BangMessageValidateBody bangMessageValidateBody = new BangMessageValidateBody();
        bangMessageValidateBody.setTxSNBinding("154545454");
        bangMessageValidateBody.setsMSValidationCode("255656");
        request.setBody(bangMessageValidateBody);
        Param s1 = PlatformPayUtil.payRequest(request);
        logger.debug("=========request json :"+s1.getMessage());

        BangMessageValidateResponse response = new BangMessageValidateResponse();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setCode("2000");
        responseHead.setMessage("成功");
        response.setHead(responseHead);
        BangMessageValidateResponseBody body = new BangMessageValidateResponseBody();
        body.setsMSValidationCode("565656");
        body.setTxSNBinding("458845");
        body.setPayCardType("01");
        body.setBankTxTime(new Date());
        response.setBangMessageValidateResponseBody(body);
        Param s2= PlatformPayUtil.payRequest(response);
        logger.debug("=========response json :"+s2.getMessage());




    }
    //快捷支付
    @Test
    public void test1() throws Exception {
        Request<PayBody> request = new Request<>();
        Head head = new Head();
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request.setHead(head);
        PayBody payBody = new PayBody();
        payBody.setTxSNBinding("45555454");
        payBody.setAmount(854515);
        payBody.setPaymentNo("8782133336");
        payBody.setSettlementFlag("1dsds");
        request.setBody(payBody);
        Param s = PlatformPayUtil.payRequest(request);
        logger.debug("=========request json :"+s.getMessage());

        Request<PayMessageValidateBody> request1 = new Request<>();
        request1.setHead(head);
        PayMessageValidateBody payMessageValidateBody = new PayMessageValidateBody();
        payMessageValidateBody.setPaymentNo("545454545");
        payMessageValidateBody.setSMSValidationCode("ds14555");
        request1.setBody(payMessageValidateBody);
         s = PlatformPayUtil.payRequest(request1);
        logger.debug("=========request json :"+s.getMessage());

        PayMessageValidateResponse response = new PayMessageValidateResponse();
        ResponseHead responseHead = new ResponseHead();
        responseHead.setCode("2000");
        responseHead.setMessage("成功");
        response.setHead(responseHead);
        PayMessageValidateResponseBody responseBody = new PayMessageValidateResponseBody();
        responseBody.setBankTxTime(new Date());
        responseBody.setInstitutionID("212121");
        responseBody.setPaymentNo("454846565");
        responseBody.setStatus(1);
        responseBody.setResponseCode("65989566");
        response.setPayMessageValidateResponseBody(responseBody);
        s = PlatformPayUtil.payRequest(response);
        logger.debug("=========request json :"+s.getMessage());
    }


    @Test
    public void test2() throws Exception {
        Request<QueryBangBody> request = new Request<>();
        Head head = new Head();
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request.setHead(head);
        QueryBangBody queryBangBody = new QueryBangBody();
        queryBangBody.setTxSNBinding("dsds");
        request.setBody(queryBangBody);
        Param s = PlatformPayUtil.payRequest(request);
        logger.debug("=========request json :"+s.getMessage());

        QueryBangResponse queryBangResponse = new QueryBangResponse();
        ResponseHead head1 = new ResponseHead();
        head1.setCode("2000");
        head1.setMessage("2dsdsdsd");
        QueryBangResponseBody queryBangResponseBody = new QueryBangResponseBody();
        queryBangResponse.setHead(head1);

        queryBangResponseBody.setTxSNBinding("dsdsds");
        queryBangResponseBody.setResponseCode("5454");
        queryBangResponseBody.setStatus(1);
        queryBangResponseBody.setInstitutionID("55dsdsd");
        queryBangResponse.setBangResponseBody(queryBangResponseBody);
        s = PlatformPayUtil.payRequest(queryBangResponse);
        logger.debug("=========response json :"+s.getMessage());
    }

    @Test
    public void test3() throws Exception {
        Request<UninstallBangBody> request = new Request<>();
        Head head = new Head();
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request.setHead(head);
        UninstallBangBody uninstallBangBody = new UninstallBangBody();
        uninstallBangBody.setTxSNBinding("dsdsds");
        uninstallBangBody.setTxSNUnBinding("4554");
        request.setBody(uninstallBangBody);
        Param s = PlatformPayUtil.payRequest(request);
        logger.info("=============="+s.getMessage());

        UninstallBangResponse uninstallBangResponse = new UninstallBangResponse();
        ResponseHead head1 = new ResponseHead();
        head1.setCode("2000");
        head1.setMessage("2dsdsdsd");
        uninstallBangResponse.setHead(head1);
        UninstallBangResponseBody uninstallBangResponseBody = new UninstallBangResponseBody();
        uninstallBangResponseBody.setInstitutionID("sdd45545454");
        uninstallBangResponseBody.setBankTxTime(new Date());
        uninstallBangResponseBody.setResponseCode("sdsdsds");
        uninstallBangResponse.setUninstallBangResponseBody(uninstallBangResponseBody);
         s = PlatformPayUtil.payRequest(uninstallBangResponse);
        logger.info("=============="+s.getMessage());
    }

    @Test
    public void test4() throws IOException {
        //MTIzNDU2111
        String s = "123456";
        byte[] b = s.getBytes();
        String encode = new BASE64Encoder().encode(b);
        logger.debug(encode);
        byte[] mtIzNDU2111s = new BASE64Decoder().decodeBuffer("MTIzNDU2111");
        logger.debug(new String(mtIzNDU2111s));
    }
}
