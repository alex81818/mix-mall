package com.calvin.mixmall.admin.system.entity;

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
import java.time.LocalDateTime;

/****
 * @Author:guowei
 * @Description:SysUser构建
 * @Date 2020-06-11
 * 系统用户表
 *****/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "SysUser")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiParam(hidden = true)
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "状态 0:禁用，1:正常")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建用户id")
    @TableField("user_id_create")
    private Long userIdCreate;

    @ApiModelProperty(value = "创建时间")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "是否删除 0 是 1 否")
    @TableField("yn")
    private Integer yn;


}

