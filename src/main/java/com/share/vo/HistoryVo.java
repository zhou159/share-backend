package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的历史记录数据")
public class HistoryVo {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "历史浏览交易物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "历史浏览出行id")
    private Integer travelId;

    @ApiModelProperty(value = "历史浏览出租id")
    private Integer rentId;

    @ApiModelProperty(value = "历史浏览最新浏览时间")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "历史记录类型(交易物品，出行，出租，帮助)")
    private String type;
}
