package com.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2021-04-17
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("authenticate")
@ApiModel(value="Authenticate对象", description="")
public class Authenticate implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审核id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "审核用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "管理员nickname")
    @TableField("admin_uid")
    private String adminUid;

    @ApiModelProperty(value = "审核图片")
    @TableField("authenticate_picture")
    private String authenticatePicture;

    @ApiModelProperty(value = "审核创建时间")
    @TableField("createtime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核状态（0：未审核，1：审核通过，2：审核失败）")
    @TableField("status")
    private String status;

}
