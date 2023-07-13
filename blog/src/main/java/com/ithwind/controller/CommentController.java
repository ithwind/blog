package com.ithwind.controller;

import com.ithwind.api.CommonResult;
import com.ithwind.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author IthWind
 * @since 2023-07-193
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @GetMapping("/commentList")
    public CommonResult<?> commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(articleId, pageNum, pageSize);
    }
    @PostMapping
    public CommonResult<?> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
