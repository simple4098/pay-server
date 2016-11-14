package com.yql.biz.support.helper;

import com.yql.biz.exception.RemoteApiCallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class RestTemplateHelper<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateHelper.class);
    private final ParameterizedTypeReference<T> responseType;

    private final transient RestTemplate restTemplate;

    protected RestTemplateHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        Class<?> parameterizedTypeReferenceSubclass = findParameterizedTypeReferenceSubclass(getClass());
        Type type = parameterizedTypeReferenceSubclass.getGenericSuperclass();
        Assert.isInstanceOf(ParameterizedType.class, type);
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Assert.isTrue(parameterizedType.getActualTypeArguments().length == 1);
        Type type1 = parameterizedType.getActualTypeArguments()[0];
        this.responseType = new InnnerParameterizedTypeReference<>(type1);
    }

    private static class InnnerParameterizedTypeReference<T> extends ParameterizedTypeReference<T> {
        private Type _type;

        InnnerParameterizedTypeReference(Type type) {
            this._type = type;
        }

        @Override
        public Type getType() {
            return this._type;
        }
    }

    private static Class<?> findParameterizedTypeReferenceSubclass(Class<?> child) {
        Class<?> parent = child.getSuperclass();
        if (Object.class == parent) {
            throw new IllegalStateException("Expected ParameterizedTypeReference superclass");
        } else if (RestTemplateHelper.class == parent) {
            return child;
        } else {
            return findParameterizedTypeReferenceSubclass(parent);
        }
    }

    public T getForObject(String url, Object... variables) {
        long startTime = System.currentTimeMillis();
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, null, responseType, variables);
        //String params = Stream.of(variables).map(String::valueOf).collect(Collectors.joining(","));
        long spentTime = System.currentTimeMillis() - startTime;
        if (exchange.getStatusCodeValue() != 200) {
            LOGGER.error("Exception on call remote api", exchange);
            throw new RemoteApiCallException(exchange.toString());
        }
        LOGGER.warn(restTemplate.getUriTemplateHandler().expand(url, variables).toString()+" Time:"+spentTime);
        return exchange.getBody();
    }
}
