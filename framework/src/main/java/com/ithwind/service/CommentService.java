package com.ithwind.service;

import com.ithwind.api.CommonResult;
import com.ithwind.domain.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-193
 */
public interface CommentService extends IService<Comment> {

    CommonResult<?> commentList(Long articleId, Integer pageNum, Integer pageSize);
}
