package com.share.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.share.service.ChatService;
import com.share.util.RedisUtil;
import com.share.vo.ChatVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/{userId}")
@Component
public class WebSocketServer {
    
    private static RedisUtil redisUtil;
    private static ChatService chatService;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil,ChatService chatService){
        WebSocketServer.redisUtil = redisUtil;
        WebSocketServer.chatService = chatService;
    }

    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId = "";

    /**
     * 打开websocket连接。
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId){
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
        }else {
            webSocketMap.put(userId,this);
        }

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭websocket连接
     */
    @OnClose
    public void onClose(){
        if (webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
        }
    }

    /**
     * 当有消息接入的时候，websocket的响应
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        if (StringUtils.isNotBlank(message)){
            try{
                JSONObject jsonObject = JSON.parseObject(message);
                jsonObject.put("fromUserId",this.userId);

                String senderAvatar = jsonObject.getString("avatar");
                String sendTime = jsonObject.getString("sendTime");

                // 接收方将发送方添加到列表中
                int value1 = jsonObject.getInteger("userId");
                int toUser1 = jsonObject.getInteger("toUserId");

                String key1 = toUser1 + "ChatPerson";
                List list = redisUtil.lrangeAll(key1);

                String toUserId=jsonObject.getString("toUserId");
                if (!list.contains(value1)){
                    redisUtil.rPush(key1,value1);
                }

                // 发送方将接收方添加到列表中
                int value2 = jsonObject.getInteger("toUserId");
                int toUser2 = jsonObject.getInteger("userId");

                String key2 = toUser2 + "ChatPerson";
                List list2 = redisUtil.lrangeAll(key2);

                if (!list2.contains(value2)){
                    redisUtil.rPush(key2,value2);
                }

                //将信息存入数据库
                ChatVo vo = new ChatVo();
                vo.setUserIdFrom(value1);
                vo.setUserIdTo(toUser1);
                vo.setMessage(jsonObject.getString("msg"));
                vo.setSendTime(sendTime);
                vo.setSenderAvatar(senderAvatar);

                //从redis中获取目标id当前聊天用户id
                String key = toUser1 + "chatUserNow";
                Object o = redisUtil.get(key);

                if (o == null){
                    vo.setStatus("0");
                }else {
                    if (new Integer((Integer) o) == value1){
                        vo.setStatus("1");
                        webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                    }else {
                        vo.setStatus("0");
                    }
                }

                //将消息存入数据库中
                chatService.addChat(vo);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            System.out.println("用户"+userId+",不在线！");
        }
    }
}
