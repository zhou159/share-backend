package com.share.ro.commentsRo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的出行评论数据")
public class TravelCommentsRo {
    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty(value = "评论人id")
    private Integer userIdCommentator;

    @ApiModelProperty(value = "评论人头像")
    private String picture;

    @ApiModelProperty(value = "评论人nickname")
    private String nickname;

    @ApiModelProperty(value = "出行id")
    private Integer travelId;

    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "评论内容")
    private String text;
}
