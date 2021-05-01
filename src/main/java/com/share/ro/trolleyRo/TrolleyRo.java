package com.share.ro.trolleyRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("后端返回的购物车数据")
public class TrolleyRo {
    @ApiModelProperty(value = "购物车id")
    private Integer id;

    @ApiModelProperty(value = "购物车用户id")
    private Integer userId;

    @ApiModelProperty(value = "购物车货物id")
    private Integer goodsId;

    @ApiModelProperty(value = "交易物品名字")
    private String goodsName;

    @ApiModelProperty(value = "交易物品图片")
    private String picture;

    @ApiModelProperty(value = "交易物品价钱")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "交易物品库存")
    private Integer stock;

    @ApiModelProperty(value = "购物车货物数量")
    private Integer number;
}
