package com.calvin.mixmall.admin.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/****
 * @Author:guowei
 * @Description:Goods构建
 * @Date 2020-06-07
 *
 *****/
@Data
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiParam(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "摘要")
    @TableField("summary")
    private String summary;

    @ApiModelProperty(value = "货号")
    @TableField("goods_no")
    private String goodsNo;

    @ApiModelProperty(value = "库存")
    @TableField("stock_quantity")
    private Integer stockQuantity;

    @ApiModelProperty(value = "市场价")
    @TableField("market_price")
    private Integer marketPrice;

    @TableField("sell_price")
    private Integer sellPrice;

    @ApiModelProperty(value = "正文")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "图片")
    @TableField("img_url")
    private String imgUrl;


}

