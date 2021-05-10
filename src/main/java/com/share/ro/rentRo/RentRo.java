package com.share.ro.rentRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的出租中房屋数据")
public class RentRo {
    @ApiModelProperty(value = "租赁id")
    private Integer id;

    @ApiModelProperty(value = "出租人id")
    private Integer userIdRent;

    @ApiModelProperty(value = "出租人头像")
    private String picture;

    @ApiModelProperty(value = "出租人昵称")
    private String nickname;

//    @ApiModelProperty(value = "租赁人id")
//    private Integer userIdRenter;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "出租价格")
    private BigDecimal price;

    @ApiModelProperty(value = "出租创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "出租修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "出租描述")
    private String details;

    @ApiModelProperty(value = "出租类型")
    private String type;

    @ApiModelProperty(value = "出租状态（0：出租中，1：被租用中，2：空闲中；3：不再对外出租；4：洽谈中")
    private String status;
}
