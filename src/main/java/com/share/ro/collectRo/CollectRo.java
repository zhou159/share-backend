package com.share.ro.collectRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的收藏数据")
public class CollectRo {

    @ApiModelProperty("收藏id")
    private Integer id;

//    @ApiModelProperty("收藏的用户id")
//    private Integer userId;

    @ApiModelProperty("收藏的交易物品id")
    private Integer goodsId;

    @ApiModelProperty("收藏的交易物品图片")
    private String picture;

    @ApiModelProperty("收藏的交易物品名字")
    private String goodsName;

    @ApiModelProperty("收藏的交易物品价格")
    private BigDecimal price;

    @ApiModelProperty("收藏创建时间")
    private LocalDateTime createTime;
}
