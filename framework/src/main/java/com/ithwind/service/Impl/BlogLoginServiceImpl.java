package com.ithwind.service.Impl;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.LoginUser;
import com.ithwind.domain.pojo.User;
import com.ithwind.domain.vo.BlogUserLoginVo;
import com.ithwind.domain.vo.UserInfoVo;
import com.ithwind.service.BlogLoginService;
import com.ithwind.util.BeanCopyUtils;
import com.ithwind.util.JwtUtil;
import com.ithwind.util.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;
    @Override
    public CommonResult<?> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //存入redis
        redisCache.setCacheObject("blogLogin:" + userId, loginUser);
        //把token和userInfo封装
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return CommonResult.success(blogUserLoginVo);
    }
}
