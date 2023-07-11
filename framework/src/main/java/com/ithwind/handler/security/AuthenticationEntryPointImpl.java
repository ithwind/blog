package com.ithwind.handler.security;

import com.alibaba.fastjson2.JSON;
import com.ithwind.api.CommonResult;
import com.ithwind.api.IErrorCode;
import com.ithwind.api.ResultCode;
import com.ithwind.util.WebUtils;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        authException.printStackTrace();
        CommonResult<?> result = null;
        if(authException instanceof BadCredentialsException){
            result = CommonResult.failed(ResultCode.LOGIN_ERROR.getCode(), authException.getMessage());
        }else if(authException instanceof InsufficientAuthenticationException){
            result = CommonResult.failed(ResultCode.UNAUTHORIZED);
        }else {
            result = CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}