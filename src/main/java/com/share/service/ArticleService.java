package com.share.service;

import com.share.entity.Article;
import com.share.entity.User;
import com.share.mapper.ArticleMapper;
import com.share.ro.ArticleRo;
import com.share.ro.UserRo;
import com.share.vo.ArticleVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public void addArticle(ArticleVo articleVo){
        articleMapper.addArticle(articleVo);
    }

    public void updateArticle(int id,ArticleVo articleVo){
        articleMapper.updateArticle(id, articleVo);
    }

    public void deleteArticle(int id){
        articleMapper.deleteArticle(id);
    }

    public List<ArticleRo> queryAllArticle(){
        return articleMapper.queryAllArticle();
    }

    public List<ArticleRo> queryArticleById(int id){
        return articleMapper.queryArticleById(id);
    }


}
