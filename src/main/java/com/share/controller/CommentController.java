package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.CommentsRo;
import com.share.service.CommentsService;
import com.share.vo.CommentsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiOperation("添加评论")
    @PostMapping("/addComments")
    public RestObject<String> addComments(@RequestBody CommentsVo commentsVo){
        if(commentsVo.getUserIdCommentator().equals("")){
            throw new ShareException("请登录!");
        }else {
            commentsVo.setCreateTime(LocalDateTime.now());
            commentService.addComments(commentsVo);
            return RestResponse.makeOKRsp("评论成功!");
        }
    }

    @ApiOperation("删除评论")
    @PostMapping("/deleteComments/{id}")
    public RestObject<String> deleteComments(@PathVariable int id){
        commentService.deleteComments(id);
        return RestResponse.makeOKRsp("删除成功!");
    }

    @ApiOperation("按用户id、出行id、信息id查询评论")
    @GetMapping("/queryById")
    public RestObject<List<CommentsRo>> queryById(@RequestBody CommentsVo commentsVo){
        return RestResponse.makeOKRsp(commentService.queryCommentsById(commentsVo));
    }
}
