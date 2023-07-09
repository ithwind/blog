package com.ithwind.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
    //文章id
    private Long id;
    //文章标题
    private String title;
    //分类Id
    private Long categoryId;
    //分类名字
    private String categoryName;
    //缩略图
    private String thumbnail;
    //文章内容
    private String content;
    //浏览量
    private Long viewCount;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
