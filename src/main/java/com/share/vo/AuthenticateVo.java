package com.share.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的审核数据")
public class AuthenticateVo {
    @ApiModelProperty(value = "审核id")
    private Integer id;

    @ApiModelProperty(value = "审核用户id")
    private Integer userId;

    @ApiModelProperty(value = "是否通过")
    private boolean success;

    @ApiModelProperty(value = "管理员nickname")
    private Integer adminUid;

    @ApiModelProperty(value = "审核图片")
    private String authenticatePicture;

    @ApiModelProperty(value = "审核创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核状态")
    private String status;
}
