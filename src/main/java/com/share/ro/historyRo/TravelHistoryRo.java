package com.share.ro.historyRo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的出行历史记录数据")
public class TravelHistoryRo {
    @ApiModelProperty(value = "历史浏览记录id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "历史浏览出行id")
    private Integer travelId;

    @ApiModelProperty(value = "出行标题")
    private String title;

    @ApiModelProperty(value = "历史浏览最新浏览时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;

}
