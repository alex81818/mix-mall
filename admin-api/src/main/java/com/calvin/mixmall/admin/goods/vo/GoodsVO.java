package com.calvin.mixmall.admin.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class GoodsVO {

    @ApiModelProperty(value = "id")
    //@NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String title;

    @ApiModelProperty(value = "货号")
    @NotBlank(message = "货号不能为空")
    private String goodsNo;

    @ApiModelProperty(value = "价格")
    @NotNull(message = "价格不能为空")
    private Integer sellPrice;

    @ApiModelProperty(value = "摘要")
    @NotBlank(message = "摘要不能为空")
    private String summary;

    @ApiModelProperty(value = "图片")
    //@NotNull(message = "图片不能为空")
    private String imgUrl;
}
