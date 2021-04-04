package com.share.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @ApiModelProperty(value = "租赁人id")
    @TableField("user_id_renter")
    private Integer userIdRenter;

    @ApiModelProperty(value = "出租类型（department:房屋；parking：停车位）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "出租屋地址")
    @TableField("department_address")
    private String departmentAddress;

    @ApiModelProperty(value = "停车位地址")
    @TableField("parking_address")
    private String parkingAddress;

    @ApiModelProperty(value = "出租价格")
    @TableField("price")
    private BigDecimal price;


}
