package com.share.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel("前端传入的聊天数据")
public class ChatVo {
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

    @ApiModelProperty(value = "当前聊天用户id")
    private Integer chatUserNow;

    @ApiModelProperty(value = "聊天信息发送者头像")
    private String senderAvatar;

    @ApiModelProperty(value = "需要批量修改状态的消息id")
    private List ids;
}
