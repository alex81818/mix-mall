package com.calvin.mixmall.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel
public class LoginReqVO {

    @ApiModelProperty(value = "手机号", required = true)
    @Pattern(regexp= "^1[0-9]{10}$", message = "手机号码格式错误")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;
}
