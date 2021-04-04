package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Comments;
import com.share.ro.CommentsRo;
import com.share.vo.CommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {
    //添加评论
    int addComments (@Param("commentsVo")CommentsVo commentsVo);

    //删除评论(id)
    int deleteComments(@Param("id")int id);

    //查询按类型id
    List<CommentsRo> queryCommentsById(@Param("commentsVo")CommentsVo commentsVo);

}
