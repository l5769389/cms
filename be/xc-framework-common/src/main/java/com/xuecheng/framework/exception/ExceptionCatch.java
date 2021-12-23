package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.Response;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException){
        ResultCode resultCode =customException.getResultCode();
        LOGGER.error("catch exception {}",customException.getMessage());
        return  new ResponseResult(resultCode);
    }
}
