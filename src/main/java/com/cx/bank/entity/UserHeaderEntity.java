package com.cx.bank.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName userHeaderEntity
 * @Description 用户头像实体
 * @Author Bruce Xu
 * @Date 2021/9/14 14:45
 * @Version 1.0
 */
@TableName("t_user_header")
@Data
public class UserHeaderEntity {
    /**
     * 图片ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String username;
    /**
     * 图片地址
     */
    private String imgUrl;
}
