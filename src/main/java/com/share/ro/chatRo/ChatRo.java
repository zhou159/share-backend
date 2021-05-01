package com.share.ro.chatRo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("后端返回的聊天信息数据")
public class ChatRo {
    @ApiModelProperty(value = "聊天信息id")
    private Integer id;

    @ApiModelProperty(value = "聊天信息发送用户id")
    private Integer userIdFrom;

    @ApiModelProperty(value = "聊天信息接收用户id")
    private Integer userIdTo;

    @ApiModelProperty(value = "聊天信息内容")
    private String message;

    @ApiModelProperty(value = "聊天信息发送时间")
    private String sendTime;

    @ApiModelProperty(value = "信息状态（0：未读，1：已读）")
    private String status;

    @ApiModelProperty(value = "聊天信息发送者头像")
    private String senderAvatar;
}
