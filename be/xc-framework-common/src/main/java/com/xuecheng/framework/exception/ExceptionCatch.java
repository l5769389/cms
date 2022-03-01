package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.Response;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //控制器增强
@ResponseBody
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    private static ImmutableMap<Class <? extends Throwable>,ResultCode> EXCEPTIONMAP;
    private static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder =ImmutableMap.builder();

    static {
        builder.put(HttpRequestMethodNotSupportedException.class,CommonCode.INVALID_PARAMS);
        EXCEPTIONMAP =builder.build();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException){
        ResultCode resultCode =customException.getResultCode();
        LOGGER.error("catch exception {}",customException.getMessage());
        return  new ResponseResult(resultCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult unknownException(Exception exception){
        LOGGER.error("catch exception {}",exception.getMessage());

        ResultCode resultCode = EXCEPTIONMAP.get(exception.getClass());
        if (resultCode!=null){
            return new ResponseResult(resultCode);
        }
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }
}
