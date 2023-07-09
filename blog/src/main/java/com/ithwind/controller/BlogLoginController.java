package com.ithwind.controller;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.User;
import com.ithwind.service.BlogLoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody User user){
        return blogLoginService.login(user);
    }
}
