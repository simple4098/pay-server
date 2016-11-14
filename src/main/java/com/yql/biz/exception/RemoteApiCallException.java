package com.yql.biz.exception;

/**
 * <p> 远程调用exception </p>
 * @auther simple
 * data 2016/11/14 0014.
 */
public class RemoteApiCallException extends RuntimeException {

    public RemoteApiCallException(String msg) {
        super(msg);
    }

}
