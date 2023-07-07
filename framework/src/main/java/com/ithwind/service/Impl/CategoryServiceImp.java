package com.ithwind.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ithwind.api.CommonResult;
import com.ithwind.constants.SystemConstants;
import com.ithwind.domain.pojo.Article;
import com.ithwind.domain.pojo.Category;
import com.ithwind.domain.mapper.CategoryMapper;
import com.ithwind.domain.vo.CategoryVo;
import com.ithwind.service.ArticleService;
import com.ithwind.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithwind.util.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-188
 */
@Service
public class CategoryServiceImp extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    ArticleService articleService;
    @Override
    public CommonResult<?> getCategoryList() {
        //查询文章表状态为已发布
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        List<Article> articleList = articleService.list(queryWrapper);
        //获取文章id并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //查询分类表

        List<Category> categoryList = listByIds(categoryIds);
        categoryList = categoryList.stream().filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);

        return CommonResult.success(categoryVos);
    }
}
