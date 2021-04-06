package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Article;
import com.share.ro.ArticleRo;
import com.share.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    //发布信息
    int addArticle(@Param("articleVo")ArticleVo articleVo);

    //修改信息
    int updateArticle(@Param("articleId")int id,@Param("articleVo")ArticleVo articleVo);

    //删除信息按id(真删)
    int deleteArticle(@Param("articleId")int id);

    //查询全部消息
    List<ArticleRo> queryAllArticle();

    //按用户id查询消息
    List<ArticleRo> queryArticleById(@Param("id")int id);

}
