package com.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("comments")
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "评论人id")
    @TableField("user_id_commentator")
    private Integer userIdCommentator;

    @ApiModelProperty(value = "被评论人id")
    @TableField("user_id_assessee")
    private Integer userIdAssessee;

    @ApiModelProperty(value = "出行id")
    @TableField("travel_id")
    private Integer travelId;

    @ApiModelProperty(value = "信息id")
    @TableField("article_id")
    private Integer articleId;

    @ApiModelProperty(value = "评论时间")
    @TableField("createtime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "评论内容")
    @TableField("text")
    private String text;

}
