package com.ithwind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    private Long id;
    private String title;
    private String categoryName;
    //缩略图
    private String thumbnail;
    private Long viewCount;
    private LocalDateTime createTime;
}
