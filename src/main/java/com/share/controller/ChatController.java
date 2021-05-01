package com.share.controller;

import com.share.config.WebSocketServer;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.chatRo.ChatRo;
import com.share.service.ChatService;
import com.share.util.RedisUtil;
import com.share.vo.ChatVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api("聊天模块")
@RestController
@RequestMapping("/chat")
@CrossOrigin()
public class ChatController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ChatService chatService;

    @PostMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(@RequestBody String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    @ApiOperation("查询联系人列表")
    @GetMapping("/chatPerson/{userId}")
    public RestObject<List> queryChatPerson(@PathVariable int userId){
        String key = userId + "ChatPerson";
        List list = redisUtil.lrangeAll(key);
        return RestResponse.makeOKRsp(list);
    }

    @ApiOperation("从联系人列表删除某个联系人")
    @PostMapping("/deleteChatPerson/{userId}")
    public RestObject<String> deleteChatPerson(@PathVariable int userId,@RequestBody ChatVo chatVo){
        String key = userId + "ChatPerson";
        System.out.println(chatVo.getUserIdTo());
        redisUtil.lrem(key,chatVo.getUserIdTo());
        List list = redisUtil.lrangeAll(key);
        if (list.contains(chatVo.getUserIdTo())){
            return RestResponse.makeErrRsp("删除失败");
        }else {
            return RestResponse.makeOKRsp("删除成功");
        }
    }

    @ApiOperation("新增联系人到联系人列表")
    @PostMapping("/addChatPerson/{userId}")
    public void addChatPerson(@PathVariable int userId, @RequestBody ChatVo chatVo){
        String key = userId + "ChatPerson";
        List list = redisUtil.lrangeAll(key);
        if (!list.contains(chatVo.getUserIdTo())){
            redisUtil.rPush(key,chatVo.getUserIdTo());
        }
    }

    @ApiOperation("删除聊天信息")
    @PostMapping("/deleteChat")
    public RestObject<String> deleteChat(@RequestBody ChatVo vo){
        String s = chatService.deleteChat(vo);
        if (s.equals("删除成功")){
            return RestResponse.makeOKRsp(s);
        }else {
            return RestResponse.makeErrRsp(s);
        }
    }

    @ApiOperation("按用户id查询聊天信息")
    @GetMapping("/queryByUserId/{userId}")
    public RestObject<List<ChatRo>> queryByUserIdFrom(@PathVariable int userId){
        List<ChatRo> chatRos = chatService.queryChatByUserId(userId);
        return RestResponse.makeOKRsp(chatRos);
    }

//    @ApiOperation("按接收者id查询聊天信息")
//    @GetMapping("/queryByUserIdTo/{userIdTo}")
//    public RestObject<List<ChatRo>> queryByUserIdTo(@PathVariable int userIdTo){
//        List<ChatRo> chatRos = chatService.queryChatByUserIdTo(userIdTo);
//        return RestResponse.makeOKRsp(chatRos);
//    }

    @ApiOperation("修改聊天信息状态")
    @PostMapping("/updateStatus")
    public RestObject<String> updateStatus(@RequestBody ChatVo chatVo){
        ChatVo vo = new ChatVo();
        vo.setStatus("1");
        String s = chatService.updateChat(chatVo);
        if (s.equals("修改成功")){
            return RestResponse.makeOKRsp(s);
        }else {
            return RestResponse.makeErrRsp(s);
        }
    }

    @ApiOperation("用户当前聊天用户")
    @PostMapping("/chatUserNow/{userId}")
    public RestObject<String> chatUserNow(@PathVariable int userId,@RequestBody ChatVo chatVo){
        String key = userId + "chatUserNow";
        String s = redisUtil.setex(key, chatVo.getChatUserNow(),15);
        if (s.equals("添加成功")){
            return RestResponse.makeOKRsp(s);
        }else {
            return RestResponse.makeErrRsp("添加失败");
        }
    }

    @ApiOperation("从redis中删除某个键")
    @PostMapping("/deleteChatUserNowKey/{userId}")
    public RestObject<String> deleteChatUserNowKey(@PathVariable int userId){
        String key = userId + "chatUserNow";
        System.out.println("调用了deleteChatUserNowKey");
        boolean s = redisUtil.del(key);
        if (s){
            return RestResponse.makeOKRsp("删除成功");
        }else {
            return RestResponse.makeErrRsp("删除失败");
        }
    }

    @ApiOperation("统计当前用户未读消息数")
    @PostMapping("/numOfUrMsg/{userId}")
    public RestObject<Integer> numOfUrMsg(@PathVariable int userId){
        return RestResponse.makeOKRsp(chatService.countAll(userId));
    }

    @ApiOperation("按发送者id和当前用户查询所有的status为0的消息条数")
    @PostMapping("/numOfUnrMsgByFT/{userId}")
    public RestObject<Integer> countUnrMsgByFT(@PathVariable int userId,@RequestBody ChatVo chatVo){
        return RestResponse.makeOKRsp(chatService.countUnrMsgByFT(userId,chatVo));
    }
}
