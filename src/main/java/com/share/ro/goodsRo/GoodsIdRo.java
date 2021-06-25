package com.share.ro.goodsRo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的单个交易物品数据")
public class GoodsIdRo {
    @ApiModelProperty("交易物品id")
    private Integer id;

    @ApiModelProperty("交易物品名字")
    private String goodsName;

    @ApiModelProperty("交易物品描述")
    private String details;

    @ApiModelProperty("交易物品价格")
    private BigDecimal price;

    @ApiModelProperty("交易物品上架时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    @ApiModelProperty(value = "交易物品状态（'出售中','已出售','已下架','交易中'）")
    private String status;

}
