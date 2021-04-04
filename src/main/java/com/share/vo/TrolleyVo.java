package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("前端传入的购物车数据")
public class TrolleyVo {
    @ApiModelProperty(value = "购物车用户id")
    private Integer userId;

    @ApiModelProperty(value = "购物车货物id")
    private Integer goodsId;

    @ApiModelProperty(value = "购物车货物数量")
    private Integer number;
}
