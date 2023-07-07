package com.ithwind.service;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-188
 */
public interface CategoryService extends IService<Category> {

    CommonResult<?> getCategoryList();
}
