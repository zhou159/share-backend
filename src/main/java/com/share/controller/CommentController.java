package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.entity.Comments;
import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.commentsRo.ArticleCommentsRo;
import com.share.ro.commentsRo.CommentsRo;
import com.share.ro.commentsRo.TravelCommentsRo;
import com.share.ro.commentsRo.UserCommentsRo;
import com.share.service.CommentsService;
import com.share.vo.CommentsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("评论模块")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentsService commentService;

    @UserLoginInfo
    @ApiOperation("添加评论")
    @PostMapping("/addComments/{userId}")
    public RestObject<String> addComments(@RequestBody CommentsVo commentsVo,@PathVariable int userId){
        commentsVo.setUserIdCommentator(userId);
        commentsVo.setCreateTime(LocalDateTime.now());
        commentService.addComments(commentsVo);
        return RestResponse.makeOKRsp("评论成功!");

    }

    @UserLoginInfo
    @ApiOperation("删除评论；id为评论id；userId为当前登录用户id")
    @PostMapping("/deleteComments/{id}/{userId}")
    public RestObject<String> deleteComments(@PathVariable int id,@PathVariable int userId){
        Comments comments = commentService.queryCommentsById(id);
        Integer userIdCommentator = comments.getUserIdCommentator();
        if (userIdCommentator == userId){
            commentService.deleteComments(id);
            return RestResponse.makeOKRsp("删除成功!");
        }else {
            return RestResponse.makeErrRsp("删除失败！您不是该条评论的主人哦");
        }
    }

    @UserLoginToken
    @ApiOperation("按用户id查询评论")
    @GetMapping("/queryByUserId/{userId}")
    public RestObject<List<UserCommentsRo>> queryByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(commentService.queryCommentsByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按出行id查询评论")
    @GetMapping("/queryByTravelId/{travelId}")
    public RestObject<List<TravelCommentsRo>> queryByTravelId(@PathVariable int travelId){
        return RestResponse.makeOKRsp(commentService.queryCommentsByTravelId(travelId));
    }

    @UserLoginToken
    @ApiOperation("按信息id查询评论")
    @GetMapping("/queryByArticleId/{articleId}")
    public RestObject<List<ArticleCommentsRo>> queryByArticleId(@PathVariable int articleId){
        return RestResponse.makeOKRsp(commentService.queryCommentsByArticleId(articleId));
    }

}
