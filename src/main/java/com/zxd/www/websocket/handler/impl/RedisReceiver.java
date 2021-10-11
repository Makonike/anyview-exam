package com.zxd.www.websocket.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.zxd.www.websocket.bean.SendMessage;
import com.zxd.www.websocket.bean.SendMessageAll;
import com.zxd.www.websocket.handler.WebsocketEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Makonike
 * @date 2021-10-10 17:13
 **/
@Component
public class RedisReceiver {

    @Autowired
    WebsocketEndpoint websocketEndpoint;

    /**
     * 点对点发送消息
     * @param message msgJson
     */
    public void sendMsg(String message){
        SendMessage msg = JSONObject.parseObject(message, SendMessage.class);
        websocketEndpoint.sendMessageById(msg.getGroupId(), msg.getUserId(), msg.getMsg());
    }

    /**
     * 群发消息
     * @param message msgJson
     */
    public void sendAllMsg(String message){
        SendMessageAll msg = JSONObject.parseObject(message, SendMessageAll.class);
        websocketEndpoint.batchSendMessage(msg.getGroupId(), msg.getMsg());
    }


}
