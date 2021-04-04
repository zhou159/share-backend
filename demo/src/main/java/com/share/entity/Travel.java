package com.share.entity;

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
@TableName("travel")
@ApiModel(value="Travel对象", description="")
public class Travel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出行id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出行人id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "出行目的地")
    @TableField("destination")
    private String destination;

    @ApiModelProperty(value = "出行出发时间")
    @TableField("departure_time")
    private LocalDateTime departureTime;

    @ApiModelProperty(value = "出行返回时间")
    @TableField("return_time")
    private LocalDateTime returnTime;

    @ApiModelProperty(value = "出行描述")
    @TableField("details")
    private String details;


}
