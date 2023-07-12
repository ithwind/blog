package com.ithwind.domain.mapper;

import com.ithwind.domain.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.Annotation;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author IthWind
 * @since 2023-07-193
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
