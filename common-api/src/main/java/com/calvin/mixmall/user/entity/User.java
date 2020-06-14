package com.calvin.mixmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/****
 * @Author:guowei
 * @Description:User构建
 * @Date 2020-06-08
 *
 *****/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_user")
@ApiModel(value = "User对象", description = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiParam(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("phone")
    private String phone;

    @TableField("password")
    private String password;

    @TableField("login_type")
    private Integer loginType;

    @TableField("open_id")
    private String openId;

    @TableField("nickname")
    private String nickname;

    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "0.普通会员 1.VIP会员")
    @TableField("level")
    private Integer level;

    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty(value = "1 男 0 女")
    @TableField("gender")
    private Integer gender;

    @TableField("status")
    private Integer status;

    @TableField("gmt_update")
    private LocalDateTime gmtUpdate;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;


}

