package com.ithwind.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author IthWind
 * @since 2023-07-190
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    @TableField("type")
    private String type;

    /**
     * 账号状态（0正常 1停用）
     */
    @TableField("status")
    private String status;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("phonenumber")
    private String phonenumber;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @TableField("sex")
    private String sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 创建人的用户id
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField("del_flag")
    private Integer delFlag;
}
