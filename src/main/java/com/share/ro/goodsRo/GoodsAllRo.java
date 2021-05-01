package com.share.ro.goodsRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的全部在售交易物品数据")
public class GoodsAllRo {
    @ApiModelProperty("交易物品id")
    private Integer id;

    @ApiModelProperty("交易物品名字")
    private String goodsName;

    @ApiModelProperty("交易物品描述")
    private String details;

    @ApiModelProperty("交易物品价格")
    private BigDecimal price;

    @ApiModelProperty("交易物品上架时间")
    private LocalDateTime createTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户头像")
    private String upicture;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("交易物品库存")
    private Integer stock;

    @ApiModelProperty("交易物品图片")
    private String gpicture;

}
