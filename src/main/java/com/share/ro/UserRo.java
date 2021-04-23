package com.share.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("后端返回的用户数据")
public class UserRo {
    @ApiModelProperty("用户id")
    private int id;

    @ApiModelProperty("用户真实姓名")
    private String trueName;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户地址")
    private String address;

    @ApiModelProperty("用户电话")
    private String tel;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户头像")
    private String picture;

    @ApiModelProperty("用户状态")
    private String status;

    @ApiModelProperty(value = "用户信誉值")
    private Integer creditScore;
}
