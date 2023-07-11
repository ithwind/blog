package com.ithwind.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    //文章id
    private Long id;
    //文章标题
    private String title;
    //分类名字
    private String categoryName;
    //缩略图
    private String thumbnail;
    //浏览量
    private Long viewCount;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
