package com.share.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zx
 * @since 2021-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods")
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易物品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "交易物品名字")
    @TableField("goodsname")
    private String goodsname;

    @ApiModelProperty(value = "交易物品描述")
    @TableField("details")
    private String details;

    @ApiModelProperty(value = "交易物品价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "交易物品上架时间")
    @TableField("createtime")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "交易物品库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty(value = "交易物品状态（0：在售；1：已售，2：下架）")
    @TableField("status")
    private String status;


}
