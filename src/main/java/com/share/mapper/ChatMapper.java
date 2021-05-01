package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Chat;
import com.share.ro.chatRo.ChatRo;
import com.share.vo.ChatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    //新增聊天信息
    int addChat(@Param("chatVo")ChatVo vo);

    //删除聊天信息
    int deleteChatById(@Param("id")int id);

    //按用户id查询发送或者接收的聊天信息
    List<ChatRo> queryChatByUserId(@Param("userId")int UserId);

    //批量修改聊天信息状态
    int updateChat(@Param("list")List<Integer> list);

    //查询具体聊天内容
    int deleteChat(@Param("chatVo")ChatVo chatVo);

    //按id查询聊天
    ChatRo selectChatById(@Param("id")int id);

    //查询当前用户所有的status为0的消息条数并计数
    int countAll(@Param("userId")int userId);

    //按发送者id和当前用户查询所有的status为0的消息条数
    int countUnrMsgByFT(@Param("userId")int userId,@Param("chatVo")ChatVo chatVo);

}
