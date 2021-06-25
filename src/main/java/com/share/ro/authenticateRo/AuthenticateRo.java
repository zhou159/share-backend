package com.share.ro.authenticateRo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ApiModelProperty(value = "审核用户联系方式")
    private String phone;

    @ApiModelProperty(value = "审核用户真实姓名")
    private String trueName;

    @ApiModelProperty(value = "审核用户昵称")
    private String nickname;

    @ApiModelProperty(value = "管理员用户id")
    private Integer adminUid;

    @ApiModelProperty(value = "审核图片")
    private String authenticatePicture;

    @ApiModelProperty(value = "审核创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
