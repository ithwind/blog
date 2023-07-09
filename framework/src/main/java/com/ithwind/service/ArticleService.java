package com.ithwind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.Article;
import com.ithwind.domain.vo.HotArticleVo;
import com.ithwind.domain.vo.PageVo;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-186
 */
public interface ArticleService extends IService<Article> {

    CommonResult<List<HotArticleVo>> hotArticleList();

    CommonResult<PageVo> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    CommonResult<?> getArticleDetail(Long id);
}
