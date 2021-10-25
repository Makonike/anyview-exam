package com.zxd.www.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxd.www.websocket.bean.SendMessage;
import com.zxd.www.websocket.bean.SendMessageAll;
import com.zxd.www.websocket.constant.WebSocketConstant;
import com.zxd.www.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息发布
 * @author Makonike
 * @date 2021-10-10 18:38
 **/
@Service
public class WebSocketImpl implements WebSocketService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendMessageAll(String groupId, String message) {
        SendMessageAll msg = new SendMessageAll();
        msg.setGroupId(groupId);
        msg.setMsg(message);
        msg.setType(WebSocketConstant.MESSAGE_TYPE_SEND_ALL);
        // 加入队列
        redisTemplate.convertAndSend(WebSocketConstant.SEND_ALL_CHANNEL, JSON.toJSONString(msg));
    }

    @Override
    public void sendMessageById(String groupId, String userId, String message) {
        SendMessage msg = new SendMessage();
        msg.setGroupId(groupId);
        msg.setUserId(userId);
        msg.setType(WebSocketConstant.MESSAGE_TYPE_SEND_ONE);
        msg.setMsg(message);
        redisTemplate.convertAndSend(WebSocketConstant.SEND_ONE_CHANNEL, JSON.toJSONString(msg));
    }

    @Override
    public void notifyOnline(String groupId, String userId) {
        String msg = groupId + "," + userId;
        redisTemplate.convertAndSend(WebSocketConstant.NOTIFY_ONLINE_CHANNEL, msg);
    }

    @Override
    public void sendStopSchedulerMessage(String message) {
        redisTemplate.convertAndSend(WebSocketConstant.STOP_SCHEDULED_CHANNEL, message);
    }

}
