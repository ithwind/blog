package com.ithwind.handler.exception;

import com.ithwind.api.CommonResult;
import com.ithwind.api.ResultCode;
import com.ithwind.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public CommonResult<?> systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现异常{ }", e);
        //从异常信息获取信息返回
        return CommonResult.failed(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public CommonResult<?> exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现异常{ }", e);
        //从异常信息获取信息返回
        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), e.getMessage());
    }
}
