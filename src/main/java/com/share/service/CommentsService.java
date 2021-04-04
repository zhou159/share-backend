package com.share.service;

import com.share.mapper.CommentsMapper;
import com.share.ro.CommentsRo;
import com.share.vo.CommentsVo;
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

    public List<CommentsRo> queryCommentsById(CommentsVo commentsVo){
        return commentMapper.queryCommentsById(commentsVo);
    }


}
