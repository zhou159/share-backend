package com.share.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("rent")
@ApiModel(value="Rent对象", description="")
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租赁id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出租人id")
    @TableField("user_id_rent")
    private Integer userIdRent;

//    @ApiModelProperty(value = "租赁人id")
//    @TableField("user_id_renter")
//    private Integer userIdRenter;

    @ApiModelProperty(value = "出租类型（department:房屋；parking：停车位）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "出租屋、车位地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "出租价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "出租状态（0：出租中，1：被租用中，2：空闲中；3：不再对外出租；4：洽谈中）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "逻辑删除（0：未删除，1：已删除）")
    @TableField("deleted")
    private int deleted;

    @ApiModelProperty(value = "出租创建时间")
    @TableField("createtime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "出租修改时间")
    @TableField("updatetime")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "出租描述")
    @TableField("details")
    private String details;


}
