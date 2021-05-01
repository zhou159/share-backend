package com.share.ro.travelRo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的出行数据")
public class TravelRo {
    @ApiModelProperty(value = "出行id")
    private Integer id;

    @ApiModelProperty(value = "出行人id")
    private Integer userId;

    @ApiModelProperty(value = "出行人头像")
    private String picture;

    @ApiModelProperty(value = "出行人昵称")
    private String nickname;

    @ApiModelProperty(value = "出行目的地")
    private String destination;

    @ApiModelProperty(value = "出行出发时间")
    private LocalDateTime departureTime;

    @ApiModelProperty(value = "出行返回时间")
    private LocalDateTime returnTime;

    @ApiModelProperty(value = "出行描述")
    private String details;

    @ApiModelProperty(value = "出行创建时间")
    private  LocalDateTime createTime;

    @ApiModelProperty(value = "出行修改时间")
    private  LocalDateTime updateTime;

    @ApiModelProperty(value = "出行状态")
    private  String status;
}
