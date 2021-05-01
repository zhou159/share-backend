package com.share.controller;

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

    @ApiOperation("发布信息")
    @PostMapping("/addArticle")
    public RestObject<String> addArticle(@RequestBody ArticleVo articleVo){
        articleVo.setCreateTime(LocalDateTime.now());
        articleService.addArticle(articleVo);
        return RestResponse.makeOKRsp("新增成功");
    }

    @ApiOperation("修改信息")
    @PostMapping("/updateArticle/{id}")
    public RestObject<String> updateArticle(@PathVariable int id, @RequestBody ArticleVo articleVo){
        articleVo.setUpdateTime(LocalDateTime.now());
        articleService.updateArticle(id,articleVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @ApiOperation("删除信息")
    @PostMapping("/deleteArticle/{id}")
    public RestObject<String> deleteArticle(@PathVariable int id){
        articleService.deleteArticle(id);
        return RestResponse.makeOKRsp("删除成功");
    }

    @ApiOperation("查询所有有效信息")
    @GetMapping("/queryAllEArticle")
    public RestObject<List<AllEArticleRo>> queryAllArticle(){
        //articleService.queryAllArticle();
        return RestResponse.makeOKRsp(articleService.queryAllArticle());
    }

    @ApiOperation("按用户id查询信息")
    @GetMapping("/queryArticleByUserId/{userId}")
    public RestObject<List<ArticleRo>> queryArticleByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(articleService.queryArticleByUserId(userId));
    }

    @ApiOperation("按id查询信息")
    @GetMapping("/queryArticleById/{id}")
    public RestObject<ArticleRo> queryArticleById(@PathVariable int id){
        return RestResponse.makeOKRsp(articleService.queryArticleById(id));
    }


}
