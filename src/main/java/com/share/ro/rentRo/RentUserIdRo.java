package com.share.ro.rentRo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的按用户id查询的所有出租车位数据")
public class RentUserIdRo {
    @ApiModelProperty(value = "租赁id")
    private Integer id;

    @ApiModelProperty(value = "出租人id")
    private Integer userIdRent;

//    @ApiModelProperty(value = "租赁人id")
//    private Integer userIdRenter;

    @ApiModelProperty(value = "车位地址")
    private String address;

    @ApiModelProperty(value = "出租价格")
    private BigDecimal price;

    @ApiModelProperty(value = "出租创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "出租修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "出租描述")
    private String details;

    @ApiModelProperty(value = "出租状态（'出租中','被租用中','暂停出租'")
    private String status;
}
