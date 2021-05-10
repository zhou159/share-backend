package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.articleRo.AllEArticleRo;
import com.share.ro.articleRo.ArticleRo;
import com.share.service.ArticleService;
import com.share.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("信息模块")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @UserLoginInfo
    @ApiOperation("发布信息。url参数：userId；body参数：title,text")
    @PostMapping("/addArticle/{userId}")
    public RestObject<String> addArticle(@RequestBody ArticleVo articleVo,@PathVariable int userId){
        String text = articleVo.getText();
        String title = articleVo.getTitle();
        if( text == null || title==null){
            throw new ShareException("有必填项未填写");
        }else {
            if (text.equals("") || title .equals("")){
                throw new ShareException("有必填项未填写");
            }else {
                articleVo.setUserId(userId);
                articleVo.setCreateTime(LocalDateTime.now());
                articleService.addArticle(articleVo);
                return RestResponse.makeOKRsp("发布成功!");
            }
        }
    }

    @UserLoginToken
    @ApiOperation("修改信息。url参数：userId，用户id、id，信息id。")
    @PostMapping("/updateArticle/{id}/{userId}")
    public RestObject<String> updateArticle(@PathVariable int id, @RequestBody ArticleVo articleVo,@PathVariable int userId){
        ArticleRo articleRo = articleService.queryArticleById(id);
        if (userId!=articleRo.getUserId()){
            return RestResponse.UserErrRsp("你无权修改!");
        }else {
            articleVo.setUpdateTime(LocalDateTime.now());
            articleService.updateArticle(id,articleVo);
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @UserLoginToken
    @ApiOperation("删除信息")
    @PostMapping("/deleteArticle/{id}/{userId}")
    public RestObject<String> deleteArticle(@PathVariable int id,@PathVariable int userId){
        ArticleRo articleRo = articleService.queryArticleById(id);
        if (userId!=articleRo.getUserId()){
            return RestResponse.UserErrRsp("你无权删除!");
        }else {
            articleService.deleteArticle(id);
            return RestResponse.makeOKRsp("删除成功!");
        }
    }

    @UserLoginToken
    @ApiOperation("查询所有有效信息")
    @GetMapping("/queryAllEArticle")
    public RestObject<List<AllEArticleRo>> queryAllArticle(){
        //articleService.queryAllArticle();
        return RestResponse.makeOKRsp(articleService.queryAllArticle());
    }

    @UserLoginToken
    @ApiOperation("按用户id查询信息")
    @GetMapping("/queryArticleByUserId/{userId}")
    public RestObject<List<ArticleRo>> queryArticleByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(articleService.queryArticleByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按id查询信息")
    @GetMapping("/queryArticleById/{id}")
    public RestObject<ArticleRo> queryArticleById(@PathVariable int id){
        return RestResponse.makeOKRsp(articleService.queryArticleById(id));
    }


}
