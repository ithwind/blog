package com.ithwind.handler.security;

import com.alibaba.fastjson2.JSON;
import com.ithwind.api.CommonResult;
import com.ithwind.api.ResultCode;
import com.ithwind.util.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        CommonResult<Object> result = CommonResult.failed(ResultCode.VALIDATE_FAILED);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
