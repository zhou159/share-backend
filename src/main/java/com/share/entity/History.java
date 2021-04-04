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
@TableName("history")
@ApiModel(value="History对象", description="")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "历史浏览记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "历史浏览交易物品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty(value = "历史浏览帮助id")
    @TableField("help_id")
    private Integer helpId;

    @ApiModelProperty(value = "历史浏览出行id")
    @TableField("travel_id")
    private Integer travelId;

    @ApiModelProperty(value = "历史浏览出租id")
    @TableField("rent_id")
    private Integer rentId;

    @ApiModelProperty(value = "历史浏览最新浏览时间")
    @TableField("lasttime")
    private LocalDateTime lastTime;


}
