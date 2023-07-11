package com.ithwind.controller;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.User;
import com.ithwind.service.BlogLoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<?> login(@RequestBody User user){
        return blogLoginService.login(user);
    }
}
