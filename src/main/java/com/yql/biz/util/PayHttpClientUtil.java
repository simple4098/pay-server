package com.yql.biz.util;

import com.yql.biz.vo.pay.fy.FyPayRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/12/7 0007.
 */
public class PayHttpClientUtil {

    private final  static  int TIME_OUT = 90000;
    private final  static  int REQUEST_SOCKET_TIME = 60000;
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private PayHttpClientUtil(){}

    public static HttpClient obtHttpClient(){
        HttpClientBuilder httpClientBuilder =HttpClientBuilder.create();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(PayHttpClientUtil.TIME_OUT)
                .setSocketTimeout(PayHttpClientUtil.REQUEST_SOCKET_TIME).build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        return httpClientBuilder.build();
    }

    public static HttpClient obtHttpClient(String proxyIp, int proxyPort){
        HttpClientBuilder httpClientBuilder =HttpClientBuilder.create();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(PayHttpClientUtil.TIME_OUT)
                .setSocketTimeout(PayHttpClientUtil.REQUEST_SOCKET_TIME).build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        //设置代理
        if(!StringUtils.isEmpty(proxyIp) && 0!=proxyPort){
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            httpClientBuilder.setProxy(proxy);
        }
        CloseableHttpClient httpClient = httpClientBuilder.build();
        return httpClient;
    }

    /**
     * http POST 请求
     * @param url  接口地址
     * @param fyPayRequest 请求对象
     * @throws Exception
     */
    public static String httpKvPost(String url, FyPayRequest fyPayRequest) throws Exception {
        HttpClient httpClient = obtHttpClient();
        HttpPost httpPost = new HttpPost(url);
        Map<String, Object> param = PlatformPayUtil.obtObjParm(fyPayRequest);
        List<NameValuePair> nameValuePairs = commonParam(param);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.defaultCharset()));
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String value = EntityUtils.toString(entity, Charset.defaultCharset());
        return value;
    }

    public static List<NameValuePair> commonParam(Map<String, Object> map) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
        }
        return nameValuePairs;
    }
}
