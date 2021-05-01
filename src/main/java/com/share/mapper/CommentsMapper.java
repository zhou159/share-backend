package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Comments;
import com.share.ro.commentsRo.ArticleCommentsRo;
import com.share.ro.commentsRo.CommentsRo;
import com.share.ro.commentsRo.TravelCommentsRo;
import com.share.ro.commentsRo.UserCommentsRo;
import com.share.vo.CommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {
    //添加评论
    int addComments (@Param("commentsVo")CommentsVo commentsVo);

    //删除评论(id)
    int deleteComments(@Param("id")Integer id);

    //按id查询评论
    Comments queryCommentsById(@Param("id")Integer id);

    //按用户id查询用户评价
    List<UserCommentsRo> queryCommentsByUserId(@Param("userId")Integer userId);

    //按出行id查询出行评价
    List<TravelCommentsRo> queryCommentsByTravelId(@Param("travelId")Integer travelId);

    //按信息id查询信息评价
    List<ArticleCommentsRo> queryCommentsByArticleId(@Param("articleId")Integer articleId);

    //对评论添加回复

}
