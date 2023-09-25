package com.ly.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * @TableName t_account
 */
@TableName(value = "t_account")
@Data
public class AccountEntity implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户名
     */
    @NotNull(message = "不能为空")
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "不能为空")
    @TableField(value = "password")
    private String password;

    /**
     * 创建时间
     */
    @TableField(value = "create_Time")
    private Date create_Time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}