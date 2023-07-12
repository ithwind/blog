package com.ithwind.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithwind.api.CommonResult;
import com.ithwind.constants.SystemConstants;
import com.ithwind.domain.pojo.Comment;
import com.ithwind.domain.mapper.CommentMapper;
import com.ithwind.domain.vo.comment.CommentVo;
import com.ithwind.domain.vo.PageVo;
import com.ithwind.domain.vo.comment.ReturnCommentVo;
import com.ithwind.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithwind.service.UserService;
import com.ithwind.util.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author IthWind
 * @since 2023-07-193
 */
@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private UserService userService;

    @Override
    public CommonResult<?> commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //查询根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<CommentVo> commentVoList = toCommentVo(page.getRecords());
        List<ReturnCommentVo> returnCommentVo = BeanCopyUtils.copyBeanList(commentVoList, ReturnCommentVo.class);
        //查询子评论
        for (CommentVo commentVo : commentVoList) {
            //查询对应子评论
            List<CommentVo> children = getChildren(commentVo.getRootId());
            commentVo.setChildren(children);
        }

        return CommonResult.success(new PageVo(returnCommentVo, page.getTotal()));
    }

    /**
     *
     * @param rootId 根评论id
     * @return 子评论集合
     */
    private List<CommentVo> getChildren(Long rootId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, rootId);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> commentList = list(queryWrapper);

        return toCommentVo(commentList);
    }

    private List<CommentVo> toCommentVo(List<Comment> list){
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //赋值用户名
        for (CommentVo commentVo : commentVoList) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //判断是否为根评论
            if(commentVo.getToCommentUserId() != -1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVoList;
    }
}
