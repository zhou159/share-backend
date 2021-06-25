package com.share.ro.helpRo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("后端返回的求助数据")
public class HelpRo {
    @ApiModelProperty("帮助id")
    private Integer id;

    @ApiModelProperty(value = "求助人id")
    private Integer userIdHelp;

    @ApiModelProperty(value = "求助人住址")
    private String userHelpAddress;

    @ApiModelProperty(value = "求助人头像")
    private String upicture;

    @ApiModelProperty(value = "求助人昵称")
    private String nickname;

    @ApiModelProperty(value = "帮助人id")
    private Integer userIdHelper;

    @ApiModelProperty(value = "帮助类型(快递，外卖)")
    private String type;

    @ApiModelProperty(value = "外卖、快递存放地址、外卖小哥等待地址")
    private String address;

    @ApiModelProperty(value = "取快递，外卖的凭证（比如：提取码，手机号，手机尾号四位数，外卖上的姓名，电话等等）")
    private String details;

    @ApiModelProperty(value = "报酬")
    private BigDecimal price;

    @ApiModelProperty(value = "帮助创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "帮助修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("交易物品状态（0：求助中；1：帮助中；2：已完成；3：已撤销；4：帮助方取消帮助；5：求租方取消帮助）")
    private String status;

}
