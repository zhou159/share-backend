package com.share.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("前端传入的出租数据")
public class RentVo {
    @ApiModelProperty(value = "出租人id")
    private Integer userIdRent;

    @ApiModelProperty(value = "租赁人id")
    private Integer userIdRenter;

    @ApiModelProperty(value = "出租类型（department:房屋；parking：停车位）")
    private String type;

    @ApiModelProperty(value = "出租屋、车位地址")
    private String address;

    @ApiModelProperty(value = "出租价格")
    private BigDecimal price;

    @ApiModelProperty(value = "出租状态（'出租中','被租用中','暂不对外出租'）")
    private String status;

    @ApiModelProperty(value = "逻辑删除（0：未删除，1：已删除）")
    private int deleted;

    @ApiModelProperty(value = "出租创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "出租修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "出租描述")
    private String details;
}
