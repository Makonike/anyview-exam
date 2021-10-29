package com.zxd.www.websocket.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.zxd.www.scheduler.service.ScheduledTask;
import com.zxd.www.websocket.bean.SendMessage;
import com.zxd.www.websocket.bean.SendMessageAll;
import com.zxd.www.websocket.bean.WebSocketBean;
import com.zxd.www.websocket.config.WsSessionManager;
import com.zxd.www.websocket.constant.WebSocketConstant;
import com.zxd.www.websocket.handler.WebsocketEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author Makonike
 * @date 2021-10-10 17:13
 **/
@Component
@Slf4j
public class RedisReceiver {

    @Autowired
    WebsocketEndpoint websocketEndpoint;

    @Autowired
    HttpAuthHandler handler;

    @Autowired
    private ScheduledTask scheduledTask;

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

    public void notifyOnline(String message) throws IOException {
        String[] split = StringUtils.split(message, ",");
        if(split == null){
            return ;
        }
        String groupId = split[0];
        String userId = split[1];
        Map<String, WebSocketBean> sMap = WsSessionManager.get(groupId);
        if(sMap == null || !sMap.containsKey(userId)){
            return ;
        }
        websocketEndpoint.sendMessageById(groupId, userId, WebSocketConstant.NOTIFY_ONLINE_MESSAGE);

        WebSocketBean remove = sMap.remove(userId);
        if(remove != null){
            // 关键点，将session手动关闭，避免了跳转页面会重复remove已登录用户的session
            remove.getSession().close();
            log.info("组别groupId:" + groupId + "，客户端userId：" + userId + "，断开连接，当前连接数：" + handler.countUser(groupId));
        }
    }

    public void stopScheduled(String message){
        scheduledTask.endTask(Integer.valueOf(message));
    }

    public void startScheduled(String message){
        log.info(message);
        scheduledTask.startTask(Integer.valueOf(message));
    }


}
