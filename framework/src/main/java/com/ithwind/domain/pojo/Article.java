package com.ithwind.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author IthWind
 * @since 2023-07-186
 */
@Getter
@Setter
@TableName("sg_article")
public class Article implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 所属分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 缩略图
     */
    @TableField("thumbnail")
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    @TableField("is_top")
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    @TableField("status")
    private String status;

    /**
     * 访问量
     */
    @TableField("view_count")
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    @TableField("is_comment")
    private String isComment;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField("del_flag")
    private Integer delFlag;
}
