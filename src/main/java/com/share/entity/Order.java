package com.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    @TableField("order_no")
    private Long orderNo;

    @ApiModelProperty(value = "订单创建时间")
    @TableField("createtime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "交易物品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "订单状态(0:未完成；1:已完成)")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "订单逻辑删除(0：未删；1：删除)")
    @TableField("deleted")
    private int deleted;




}
