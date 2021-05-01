package com.share.ro.historyRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的出行历史记录数据")
public class RentHsitoryRo {
    @ApiModelProperty(value = "历史浏览记录id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "历史浏览出行id")
    private Integer rentId;

    @ApiModelProperty(value = "出租地址")
    private String address;

    @ApiModelProperty(value = "历史浏览最新浏览时间")
    private LocalDateTime lastTime;
}
