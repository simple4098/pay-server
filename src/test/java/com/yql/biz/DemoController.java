package com.yql.biz;

import com.alibaba.fastjson.JSON;
import com.yql.biz.client.*;
import com.yql.biz.conf.SecurityConfiguration;
import com.yql.biz.dao.IBankInfoDao;
import com.yql.biz.enums.MessageType;
import com.yql.biz.enums.fy.FyRequestType;
import com.yql.biz.model.BankInfo;
import com.yql.biz.support.helper.IPayOrderParamHelper;
import com.yql.biz.util.PayUtil;
import com.yql.biz.util.PlatformPayUtil;
import com.yql.biz.vo.AccountVo;
import com.yql.biz.vo.pay.Param;
import com.yql.biz.vo.pay.fy.*;
import com.yql.biz.vo.pay.request.BangBody;
import com.yql.biz.vo.pay.request.Head;
import com.yql.biz.vo.pay.request.Request;
import com.yql.biz.vo.pay.response.Response;
import com.yql.core.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 模拟调用第三方支付接口 </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
@RestController
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
   /* @Resource
    private ComputeClient computeClient;*/
    /*@Autowired
    private DiscoveryClient discoveryClient;*/
    @Resource
    private IUserCenterClient userCenterClient;
    @Resource
    private SecurityConfiguration securityConfiguration;
    @Resource
    private IBankInfoDao bankInfoDao;
    @Resource
    private IPayOrderParamHelper payOrderCardParamHelper;
    @Resource
    private IAccountClient accountClient;
    @Resource
    private IFyCheckCardPayClient fyPayClient;
    @Resource
    private IFyPayForClient fyPayForClient;
    @Resource
    private IMessageServerClient messageServerClient;

    @RequestMapping(value = "/sendMessage")
    public ResponseModel index3(String phone) {
        ResponseModel responseModel = messageServerClient.getPhoneAuthCode(MessageType.UPDATE_PASSWORD.name(), phone, phone+"key");
        return responseModel;
    }

    @RequestMapping(value = "/index")
    public ResponseModel index() throws Exception {
        CheckCardRequest checkCardRequest = new CheckCardRequest();
        checkCardRequest.setMchntCd("0002900F0096235");
        //checkCardRequest.setMno("15198003270");
        checkCardRequest.setOcerNo("510321199105252693");
        checkCardRequest.setOno("4033920022692893");
        checkCardRequest.setoCerTp("0");
        checkCardRequest.setOnm("谢宗明");
        checkCardRequest.setoSsn(PayUtil.randomCode(30));
        String md5String = checkCardRequest.toMd5String("5old71wihg2tqjug9kkpxnhx9hiujoqj");
        checkCardRequest.setSign(md5String);
        String xml = PlatformPayUtil.payRequestXml(checkCardRequest);
        logger.debug(xml+"====sign======"+md5String);
        CheckCardResponse checkCardResponse1 = fyPayClient.checkCard(xml);
       return ResponseModel.SUCCESS(checkCardResponse1);
    }

    @RequestMapping(value = "/checkId")
    public ResponseModel checkId() throws Exception {
        CheckIDCardRequest checkCardRequest = new CheckIDCardRequest();
        checkCardRequest.setMchntCd("0002900F0096235");
        checkCardRequest.setIdNo("513721199008187775");
        checkCardRequest.setVersion("1.0");
        checkCardRequest.setName("马龙");
        String md5String = checkCardRequest.toMd5String("5old71wihg2tqjug9kkpxnhx9hiujoqj");
        checkCardRequest.setSign(md5String);
        String xml = PlatformPayUtil.payRequestXml(checkCardRequest);
        logger.debug(xml+"====sign======"+md5String);
        CheckIDCardResponse checkIDCardResponse = fyPayClient.checkIDCard(xml);
       return ResponseModel.SUCCESS(checkIDCardResponse);
    }

    @RequestMapping(value = "/payFor")
    public ResponseModel indexFor() throws Exception {
        FyPayForRequest request = new FyPayForRequest("0302","6510","6217711002734000","马龙",200,"15198003270");
        String payRequestXml = PlatformPayUtil.payRequestXml(request);
        FyPayRequest fyPayRequest = new FyPayRequest("0002900F0345178", FyRequestType.payforreq,payRequestXml);
        String md5String = fyPayRequest.toMd5String("123456");
        fyPayRequest.setMac(md5String);
        System.out.println("发送报文"+ payRequestXml);
        System.out.println("请求参数"+ JSON.toJSONString(fyPayRequest));
        //String s = fyPayForClient.payFor(reqType,xml,mac,merid);
        FyPayForResponse s1 = fyPayForClient.payFor(fyPayRequest);
       return ResponseModel.SUCCESS(s1);
    }

    @RequestMapping(value = "/index1")
    public ResponseModel index1() throws Exception {
        BankInfo one = bankInfoDao.getOne(1);
       return ResponseModel.SUCCESS();
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public ResponseModel pongMessage( String userCode) {
      /*  ResponseModel<UserBasicInfoVo> responseModel = userCenterClient.getBaseUserInfo(userCode);
        ResponseModel<UserBasicInfoVo> responseModel1 = Optional.ofNullable(responseModel).orElseThrow(DataObjectNotFoundException::new);*/
        ResponseModel<AccountVo> jaq85s2n = accountClient.getAccount("jaq85s2n");
        return jaq85s2n;
    }

   /* @RequestMapping("/add-feign")
    public ResponseModel addTest(){
        Request<BangBody> request1 = new Request<>();
        Head head = new Head() ;
        head.setInstitutionID("sdsdsdsd111");
        head.setTxCode("1245450");
        request1.setHead(head);
        BangBody bangBody = new BangBody();
        bangBody.setAccountName("张琳");
        request1.setBody(bangBody);
        Param param = new Param();
        param.setMessage("lin");
        param.setSignature("zhanglin");
        //ComputeClient computeClient =  Feign.builder().target(ComputeClient.class, "http://localhost:8080/");
        //Response response = computeClient.add1(param);
        Response response1 = computeClient.add2("hello","zhangLin");
        System.out.println("==========="+response1);
     *//*   Integer add = computeClient.add(1, 10);
        System.out.println("=========="+add);*//*
        return ResponseModel.SUCCESS(response1);
    }*/
/*
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }*/

    @RequestMapping(value = "/send" ,method = RequestMethod.GET)
    public Integer send() {
        String userCode = "jOFrRhYa";
       // messagePublisher.send(new TextMessage("TEST_USER_REGISTER", "PAY-SERVER-TAG", EventTypeKey.USER_REAL_NAME_AUTH.name(), userCode));
        return 0;
    }
}
