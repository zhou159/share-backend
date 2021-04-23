package com.share.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的订单数据")
public class OrderRo {
    @ApiModelProperty(value = "订单id")
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "交易物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单状态(0:未完成；1:已完成)")
    private String status;

    @ApiModelProperty(value = "订单逻辑删除(0：未删；1：删除)")
    private int deleted;
}
