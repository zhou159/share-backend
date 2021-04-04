package com.share.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的收藏数据")
public class CollectRo {

    @ApiModelProperty("收藏id")
    private Integer id;

    @ApiModelProperty("收藏的用户id")
    private Integer userId;

    @ApiModelProperty("收藏的交易物品id")
    private Integer goodsId;

    @ApiModelProperty("收藏创建时间")
    private LocalDateTime createTime;
}
