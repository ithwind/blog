package com.ithwind.service;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.User;

public interface BlogLoginService {
    CommonResult<?> login(User user);
}
