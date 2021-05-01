package com.share.service;

import com.share.mapper.ChatMapper;
import com.share.ro.chatRo.ChatRo;
import com.share.vo.ChatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatMapper chatMapper;

    //新增聊天信息
    public String addChat(ChatVo vo){
        int i = chatMapper.addChat(vo);
        if (i==0){
            return "添加失败";
        }else {
            return "添加成功";
        }
    }

    //删除聊天信息
    public String deleteChatById(int id){
        int i = chatMapper.deleteChatById(id);
        if (i==0){
            return "删除失败";
        }else {
            return "删除成功";
        }
    }

    //按用户id查询发送或者接收的聊天信息
    public List<ChatRo> queryChatByUserId(int UserId){
        return chatMapper.queryChatByUserId(UserId);
    }

    //按接收者id查询聊天信息
//    public List<ChatRo> queryChatByUserIdTo(int UserIdTo){
//        return chatMapper.queryChatByUserIdTo(UserIdTo);
//    }

    //修改聊天信息状态
    public String updateChat(ChatVo vo){
        int i = chatMapper.updateChat(vo.getIds());
        if (i==0){
            return "修改失败";
        }else {
            return "修改成功";
        }
    }
    //

    //按聊天id查询
    public String deleteChat(ChatVo chatVo){
        int i = chatMapper.deleteChat(chatVo);
        if (i==0){
            return "删除失败";
        }else {
            return "删除成功";
        }
    }

    //查询status为0的消息条数并计数
    public int countAll(int userId){
        return chatMapper.countAll(userId);
    }

    //按发送者id和当前用户查询所有的status为0的消息条数
    public int countUnrMsgByFT(int userId,ChatVo chatVo){
        return chatMapper.countUnrMsgByFT(userId,chatVo);
    }
}
