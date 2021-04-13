package com.share.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的交易物品数据")
public class GoodsRo {
    @ApiModelProperty("交易物品id")
    private Integer id;

    @ApiModelProperty("交易物品名字")
    private String goodsName;

    @ApiModelProperty("交易物品描述")
    private String details;

    @ApiModelProperty("交易物品价格")
    private BigDecimal price;

    @ApiModelProperty("交易物品上架时间")
    private LocalDateTime createTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("交易物品库存")
    private Integer stock;

    @ApiModelProperty("交易物品图片")
    private String picture;

    @ApiModelProperty("交易物品状态（0：在售；1：已售，2：下架）")
    private String status;
}