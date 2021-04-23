package com.share.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("后端返回的身份证信息")
public class IDCardRo {
    @ApiModelProperty("真实姓名")
    private String trueName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String IDNumber;
}
