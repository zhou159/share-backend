package com.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id",required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("truename")
    private String truename;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "用户账号")
    @TableField("useraccount")
    private String useraccount;

    @ApiModelProperty(value = "用户密码",required = true)
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户住址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "用户电话")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "用户性别(男，女，保密)")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "用户角色(a:管理，g:普通用户)")
    @TableField("roles")
    private String roles;

    @ApiModelProperty(value = "用户是否认证（0：未认证，1：已认证）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "用户头像")
    @TableField("picture")
    private String picture;


}
