package com.share.service;

import com.share.mapper.ArticleMapper;
import com.share.ro.articleRo.AllEArticleRo;
import com.share.ro.articleRo.ArticleRo;
import com.share.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AllEArticleRo> queryAllArticle(){
        return articleMapper.queryAllArticle();
    }

    public ArticleRo queryArticleById(int id){
        return articleMapper.queryArticleById(id);
    }

    public List<ArticleRo> queryArticleByUserId(int userId){
        return articleMapper.queryArticleByUserId(userId);
    }


}
