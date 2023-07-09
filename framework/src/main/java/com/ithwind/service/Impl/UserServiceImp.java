package com.ithwind.service.Impl;

import com.ithwind.domain.pojo.User;
import com.ithwind.domain.mapper.UserMapper;
import com.ithwind.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-190
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}
