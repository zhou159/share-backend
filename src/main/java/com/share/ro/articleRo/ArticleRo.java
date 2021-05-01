package com.share.ro.articleRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的信息数据")
public class ArticleRo {

    @ApiModelProperty("信息id")
    private Integer id;

    @ApiModelProperty("信息标题")
    private String title;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户头像")
    private String picture;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("信息创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("信息更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("信息内容")
    private String text;

    @ApiModelProperty("信息状态")
    private String status;
}
