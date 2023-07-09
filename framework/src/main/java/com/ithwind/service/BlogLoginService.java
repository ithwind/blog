package com.ithwind.service;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.User;

public interface BlogLoginService {
    public CommonResult<?> login(User user);
}
