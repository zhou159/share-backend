package com.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的求助数据")
public class HelpVo {

    @ApiModelProperty(value = "求助人id")
    private Integer userIdHelp;

    @ApiModelProperty(value = "帮助人id")
    private Integer userIdHelper;

    @ApiModelProperty(value = "帮助类型(快递，外卖)")
    private String type;

    @ApiModelProperty(value = "外卖存放地址、外卖小哥等待地址")
    private String deliveryAddress;

    @ApiModelProperty(value = "外卖小哥电话")
    private String deliveryManTel;

    @ApiModelProperty(value = "快递存放地址")
    private String parcelAddress;

    @ApiModelProperty(value = "取快递的凭证（比如：提取码，手机号，手机尾号四位数。。）")
    private String parcelProof;

    @ApiModelProperty(value = "报酬")
    private BigDecimal price;

    @ApiModelProperty(value = "帮助创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "帮助修改时间")
    private LocalDateTime updateTime;

}
