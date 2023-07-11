package com.ithwind.filter;


import com.alibaba.fastjson2.JSON;
import com.ithwind.api.CommonResult;
import com.ithwind.api.ResultCode;
import com.ithwind.domain.pojo.LoginUser;
import com.ithwind.util.JwtUtil;
import com.ithwind.util.RedisCache;
import com.ithwind.util.WebUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response,@Nullable FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录  直接放行
            if (filterChain != null) {
                filterChain.doFilter(request, response);
            }
            return;
        }
        //解析获取userid
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时  token非法
            //响应告诉前端需要重新登录
            CommonResult<ResultCode> result = CommonResult.unauthorized(ResultCode.UNAUTHORIZED);
            if (response != null) {
                WebUtils.renderString(response, JSON.toJSONString(result));
            }
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("blogLogin:" + userId);
        //如果获取不到
        if(Objects.isNull(loginUser)){
            //说明登录过期  提示重新登录
            CommonResult<ResultCode> result = CommonResult.unauthorized(ResultCode.UNAUTHORIZED);
            if (response != null) {
                WebUtils.renderString(response, JSON.toJSONString(result));
            }
            return;
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        if (filterChain != null) {
            filterChain.doFilter(request, response);
        }
    }
}
