package com.ithwind.domain.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnCommentVo {
    private Long articleId;
    private List<CommentVo> children;
}
