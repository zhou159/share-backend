package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的收藏数据")
public class CollectVo {
    @ApiModelProperty("收藏的用户id")
    private Integer userId;

    @ApiModelProperty("收藏的交易物品id")
    private Integer goodsId;

    @ApiModelProperty("收藏创建时间")
    private LocalDateTime createTime;
}
