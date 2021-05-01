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
@TableName("help")
@ApiModel(value="Help对象", description="")
public class Help implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帮助id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "求助人id")
    @TableField("user_id_help")
    private Integer userIdHelp;

    @ApiModelProperty(value = "帮助人id")
    @TableField("user_id_helper")
    private Integer userIdHelper;

    @ApiModelProperty(value = "帮助类型(快递，外卖)")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "外卖、快递存放地址、外卖小哥等待地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "取快递，外卖的凭证（比如：提取码，手机号，手机尾号四位数，外卖上的姓名，电话等等）")
    @TableField("details")
    private String details;

    @ApiModelProperty(value = "报酬")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "帮助创建时间")
    @TableField("createtime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "帮助修改时间")
    @TableField("updatetime")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "帮助状态")
    @TableField("status")
    private String status;

}
