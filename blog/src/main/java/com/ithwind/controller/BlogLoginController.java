package com.ithwind.controller;

import com.ithwind.api.CommonResult;
import com.ithwind.api.ResultCode;
import com.ithwind.domain.pojo.User;
import com.ithwind.exception.SystemException;
import com.ithwind.service.BlogLoginService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<?> login(@RequestBody User user){
        //判断传入参数是否为空
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(ResultCode.REQUIRE_NAME);
        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<?> logout(){
        return blogLoginService.logout();
    }
}
