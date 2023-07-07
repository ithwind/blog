package com.ithwind.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithwind.domain.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author IthWind
 * @since 2023-07-186
 */
@Mapper
public interface SgArticleMapper extends BaseMapper<Article> {

}
