package com.ithwind.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithwind.api.CommonResult;
import com.ithwind.constants.SystemConstants;
import com.ithwind.domain.mapper.SgArticleMapper;
import com.ithwind.domain.pojo.Article;
import com.ithwind.domain.vo.HotArticleVo;
import com.ithwind.service.ArticleService;
import com.ithwind.util.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-186
 */
@Service
public class ArticleServiceImp extends ServiceImpl<SgArticleMapper, Article> implements ArticleService {

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
}
