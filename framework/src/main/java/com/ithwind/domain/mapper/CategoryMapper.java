package com.ithwind.domain.mapper;

import com.ithwind.domain.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author IthWind
 * @since 2023-07-188
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
