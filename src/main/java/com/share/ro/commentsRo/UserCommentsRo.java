package com.share.ro.commentsRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的用户评价数据")
public class UserCommentsRo {
    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty(value = "评论人id")
    private Integer userIdCommentator;

    @ApiModelProperty(value = "评论人头像")
    private String picture;

    @ApiModelProperty(value = "评论人nickname")
    private String nickname;

    @ApiModelProperty(value = "被评论人id")
    private Integer userIdAssessee;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "评论内容")
    private String text;
}
