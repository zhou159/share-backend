package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的评论数据")
public class CommentsVo {

    @ApiModelProperty(value = "评论人id")
    private Integer userIdCommentator;

    @ApiModelProperty(value = "被评论人id")
    private Integer userIdAssessee;

    @ApiModelProperty(value = "出行id")
    private Integer travelId;

    @ApiModelProperty(value = "信息id")
    private Integer articleId;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "评论内容")
    private String text;

}
