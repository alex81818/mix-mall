package com.calvin.mixmall.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "性别")
    private String sex;

}
