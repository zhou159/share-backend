package com.share.ro.orderRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的用户订单数据")
public class UserOrderRo {
    @ApiModelProperty(value = "订单id")
    private Integer id;

    @ApiModelProperty(value = "交易物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "交易物品名字")
    private String goodsName;

    @ApiModelProperty(value = "交易物品图片")
    private String gPicture;

    @ApiModelProperty(value = "交易物品价钱")
    private String goodsPrice;

    @ApiModelProperty(value = "订单状态(0:正在交易；1:已完成；2:取消)")
    private String status;

}
