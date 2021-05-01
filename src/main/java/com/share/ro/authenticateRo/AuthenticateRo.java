package com.share.ro.authenticateRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的审核数据")
public class AuthenticateRo {
    @ApiModelProperty(value = "审核id")
    private Integer id;

    @ApiModelProperty(value = "审核用户id")
    private Integer userId;

    @ApiModelProperty(value = "审核用户头像")
    private String picture;

    @ApiModelProperty(value = "审核用户昵称")
    private String nickname;

    @ApiModelProperty(value = "管理员nickname")
    private Integer adminUid;

    @ApiModelProperty(value = "审核图片")
    private String authenticatePicture;

    @ApiModelProperty(value = "审核创建时间")
    private LocalDateTime createTime;

}
