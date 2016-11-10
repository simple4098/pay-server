package com.yql.biz.support.exception;

import com.yql.biz.exception.MessageRuntimeException;
import com.yql.biz.web.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MessageRuntimeException.class)
    @ResponseBody
    public ResponseModel<String> jsonErrorHandler(MessageRuntimeException e) throws Exception {
        return ResponseModel.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
