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

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat")
@ApiModel(value="Chat对象", description="")
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "聊天信息id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "聊天信息发送用户id")
    @TableField("user_id_from")
    private Integer userIdFrom;

    @ApiModelProperty(value = "聊天信息接收用户id")
    @TableField("user_id_to")
    private Integer userIdTo;

    @ApiModelProperty(value = "聊天信息内容")
    @TableField("message")
    private String message;

    @ApiModelProperty(value = "聊天信息发送时间")
    @TableField("send_time")
    private String sendTime;

    @ApiModelProperty(value = "信息状态（0：未读，1：已读）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "聊天信息发送者头像")
    @TableField("sender_avatar")
    private String senderAvatar;

}
