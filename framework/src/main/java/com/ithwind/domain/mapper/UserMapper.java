package com.ithwind.domain.mapper;

import com.ithwind.domain.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author IthWind
 * @since 2023-07-190
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
