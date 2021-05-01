package com.share.service;

import com.share.entity.Comments;
import com.share.mapper.CommentsMapper;
import com.share.ro.commentsRo.ArticleCommentsRo;
import com.share.ro.commentsRo.CommentsRo;
import com.share.ro.commentsRo.TravelCommentsRo;
import com.share.ro.commentsRo.UserCommentsRo;
import com.share.vo.CommentsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    CommentsMapper commentMapper;

    public void addComments(CommentsVo commentsVo){
        commentMapper.addComments(commentsVo);
    }

    public void deleteComments(int id){
        commentMapper.deleteById(id);
    }

    //按id查询评论
    public Comments queryCommentsById(Integer id){
        return commentMapper.queryCommentsById(id);
    }

    //按用户id查询用户评价
    public List<UserCommentsRo> queryCommentsByUserId(int userId){
        return commentMapper.queryCommentsByUserId(userId);
    }

    //按出行id查询出行评价
    public List<TravelCommentsRo> queryCommentsByTravelId(int travelId) {
        return commentMapper.queryCommentsByTravelId(travelId);
    }

    //按信息id查询信息评价
    public List<ArticleCommentsRo> queryCommentsByArticleId(int articleId) {
        return commentMapper.queryCommentsByArticleId(articleId);
    }


}
