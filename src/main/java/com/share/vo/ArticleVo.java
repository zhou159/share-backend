package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的信息数据")
public class ArticleVo {
    @ApiModelProperty("信息id")
    private Integer id;

    @ApiModelProperty("信息标题")
    private String title;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("信息创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("信息更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("信息内容")
    private String text;
}
