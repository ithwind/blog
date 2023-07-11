package com.ithwind.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author IthWind
 * @since 2023-07-188
 */
@Getter
@Setter
@TableName("sg_category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名
     */
    @TableField("name")
    private String name;

    /**
     * 父分类id，如果没有父分类为-1
     */
    @TableField("pid")
    private Long pid;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 状态0:正常,1禁用
     */
    @TableField("status")
    private String status;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private Date createTime;

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
