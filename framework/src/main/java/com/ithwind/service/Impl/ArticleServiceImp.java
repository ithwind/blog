package com.ithwind.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.ithwind.api.CommonResult;
import com.ithwind.constants.SystemConstants;
import com.ithwind.domain.mapper.SgArticleMapper;
import com.ithwind.domain.pojo.Article;
import com.ithwind.domain.pojo.Category;
import com.ithwind.domain.vo.ArticleDetailVo;
import com.ithwind.domain.vo.ArticleListVo;
import com.ithwind.domain.vo.HotArticleVo;
import com.ithwind.domain.vo.PageVo;
import com.ithwind.service.ArticleService;
import com.ithwind.service.CategoryService;
import com.ithwind.util.BeanCopyUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-186
 */
@Service
@Slf4j
public class ArticleServiceImp extends ServiceImpl<SgArticleMapper, Article> implements ArticleService {

    @Resource@Lazy
    CategoryService categoryService;
    @Override
    public CommonResult<List<HotArticleVo>> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        queryWrapper.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        //bean拷贝
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return CommonResult.success(hotArticleVos);
    }

    @Override
    public CommonResult<PageVo> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        queryWrapper.orderByDesc(Article::getIsTop);

        page(page, queryWrapper);

        //查询categoryName
        List<Article> articles = page.getRecords();
          //根据id查询
        //第一种
        /*for (Article article : articles) {
            Long id = article.getCategoryId();
            Category category = categoryService.getById(id);
            article.setCategoryName(category.getName());
        }*/
        //第二种
        articles.stream()
                .peek(article -> {
                    //获取分类id
                    Category category = categoryService.getById(article.getCategoryId());
                    article.setCategoryName(category.getName());
                }).collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return CommonResult.success(pageVo);
    }

    @Override
    public CommonResult<?> getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return CommonResult.success(articleDetailVo);
    }
}
