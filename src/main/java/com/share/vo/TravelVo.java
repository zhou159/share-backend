package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的出行数据")
public class TravelVo {
    @ApiModelProperty(value = "出行人id")
    private Integer userId;

    @ApiModelProperty(value = "出行目的地")
    private String destination;

    @ApiModelProperty(value = "出行出发时间")
    private String departureTime;

    @ApiModelProperty(value = "出行返回时间")
    private String returnTime;

    @ApiModelProperty(value = "出行描述")
    private String details;

    @ApiModelProperty(value = "出行创建时间")
    private  LocalDateTime createTime;

    @ApiModelProperty(value = "出行修改时间")
    private  LocalDateTime updateTime;

    @ApiModelProperty(value = "出行状态")
    private  String status;

    @ApiModelProperty(value = "出行逻辑删除")
    private  int deleted;

    @ApiModelProperty(value = "出行title")
    private String title;
}
