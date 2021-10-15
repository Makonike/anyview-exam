package com.zxd.www.websocket.handler.impl;

import com.zxd.www.websocket.bean.WebSocketBean;
import com.zxd.www.websocket.config.WsSessionManager;
import com.zxd.www.websocket.constant.WebSocketConstant;
import com.zxd.www.websocket.handler.WebsocketEndpoint;
import com.zxd.www.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过继承 TextWebSocketHandler 类并覆盖相应方法, 对 websocket 的事件进行处理
 * @author Makonike
 * @date 2021-10-10 9:53
 **/
@Component
@Slf4j
public class HttpAuthHandler extends TextWebSocketHandler implements WebsocketEndpoint {

    /**
     * 错误最大重试次数
     */
    private static final int MAX_ERROR_NUM = 3;

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 在 socket 连接成功后被触发，同原生注解里的 @OnOpen 功能
     * @param session session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        WebSocketBean bean = new WebSocketBean();
        String groupId = (String) session.getAttributes().get(WebSocketConstant.GROUP_ID);
        String userId = (String) session.getAttributes().get(WebSocketConstant.USER_ID);
        bean.setSession(session);
        Map<String, WebSocketBean> beanMap = WsSessionManager.get(groupId);
        // 如果存在则加入
        if(beanMap != null){
            WebSocketBean stu = beanMap.get(userId);
            //
            if(stu == null){
                // 如果客户端未连接
                beanMap.put(userId, bean);
                log.info("组别：groupId:" + groupId + "，客户端userId:" + userId + "，连接服务器，当前连接数为：" + countUser(groupId));
            } else {
                // 如果客户端已连接，则不重复连接
                log.info("组别groupId：" + groupId + "，客户端userId:" + userId + "，重复登录！");
            }
        } else {
            // 不存在则创建
            // 用户id+session
            Map<String, WebSocketBean> cMap = new ConcurrentHashMap<>(1);
            cMap.put(userId, bean);
            // 组别id+map
            WsSessionManager.add(groupId, cMap);
            log.info("组别：groupId:" + groupId + "，客户端userId:" + userId + "，连接服务器，当前连接数为：" + countUser(groupId));
        }

    }

    /**
     * 在客户端发送信息时触发，同原生注解里的 @OnMessage 功能
     * @param session session
     * @param message message
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String groupId = (String) session.getAttributes().get(WebSocketConstant.GROUP_ID);
        String userId = (String) session.getAttributes().get(WebSocketConstant.USER_ID);
        log.info("组别groupId:" + groupId + "，连接数：" + countUser(groupId) + "，发送消息：" + message);
        try{
            // 发送消息
            if(session.isOpen()){
                // TODO: 备份至mysql
                // 只要是群发都广播，否则就单发
                if(Objects.equals(groupId, userId)){
                    webSocketService.sendMessageAll(groupId, message.getPayload());
                } else {
                    // 一般不会走这边，这里我打算把给单个用户发送信息封装成一个接口，也方便调用
                    Map<String, WebSocketBean> sMap = WsSessionManager.get(groupId);
                    // 先判断本服务器端有无该用户session，如果有，则直接发，没有则广播session
                    if(sMap.containsKey(userId)){
                        sendMessageById(groupId, userId, message.getPayload());
                    } else {
                        webSocketService.sendMessageById(groupId, userId, message.getPayload());
                    }
                }
            }
            // 清空错误计数
            cleanErrorNum(groupId, userId);
        } catch (Exception e){
            log.info("组别groupId:" + groupId + "，连接数：" + countUser(groupId) + "，发送消息失败：" + message);
            int errorNum = getErrorLinkCount(groupId, userId);

            if(errorNum <= MAX_ERROR_NUM){
                handleTextMessage(session, message);
            } else {
                log.error("ws发送信息失败超过最大次数，请检查系统错误");
                // 清空错误计数
                cleanErrorNum(groupId, userId);
            }
            e.printStackTrace();
        }
    }

    /**
     * 在 socket 连接关闭后被触发，同原生注解里的 @OnClose 功能
     * 客户端断开连接，移除websocket对象
     * @param session session
     * @param status status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = (String) session.getAttributes().get(WebSocketConstant.USER_ID);
        String groupId = (String) session.getAttributes().get(WebSocketConstant.GROUP_ID);
        Map<String, WebSocketBean> map = WsSessionManager.get(groupId);

        // 清空session
//        if(remove){
//            log.info("组别groupId:" + groupId + "客户端userId：" + userId +"，断开与服务器连接");
//        }
        // 学生关闭客户端
        if(map != null){
            WebSocketBean remove = map.remove(userId);
            if(remove != null){
                log.info("组别groupId:" + groupId + "，客户端userId：" + userId + "，断开连接，当前连接数：" + countUser(groupId));
            }
        }

    }

    /**
     * 抛出异常时处理，相当于原生注解里的 @OnError 功能
     * @param session session
     * @param exception exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        exception.printStackTrace();
//        String userId = (String) session.getAttributes().get("userId");
//        String groupId = (String) session.getAttributes().get("groupId");
//        log.error("websocket发生错误==组别：" + groupId + "，客户端userId:" + userId, exception.getMessage());
    }

    /**
     * 计算当前连接数
     * @param groupId groupId
     */
    private Integer countUser(String groupId){
        int size = 0;
        Map<String,WebSocketBean> cMap = WsSessionManager.get(groupId);
        if(cMap != null){
            size = cMap.size();
        }
        return size;
    }

    /**
     * 清空错误计数
     * @param groupId 组id
     * @param userId 用户id
     */
    private void cleanErrorNum(String groupId, String userId){
        Map<String, WebSocketBean> cMap = WsSessionManager.get(groupId);
        if(cMap != null){
            WebSocketBean bean = cMap.get(userId);
            if(bean != null){
                bean.cleanErrorNum();
            }
        }
    }

    /**
     * 获取错误计数，并使得错误计数自增1
     * @param groupId 组id
     * @param userId 用户id
     */
    private Integer getErrorLinkCount(String groupId, String userId){
        int a = 0;
        Map<String, WebSocketBean> cMap = WsSessionManager.get(groupId);
        if(cMap != null){
            WebSocketBean bean = cMap.get(userId);
            if(bean != null){
                a = bean.getErrorLinkCount();
            }
        }
        return a;
    }

    /**
     * 群发消息
     * @param groupId groupId
     * @param message userId
     */
    @Override
    public void batchSendMessage(String groupId, String message) {
        Map<String, WebSocketBean> cMap = WsSessionManager.get(groupId);
        if(cMap != null){
            Set<Map.Entry<String, WebSocketBean>> set = cMap.entrySet();
            try {
                for (Map.Entry<String, WebSocketBean> s : set) {
                    s.getValue().getSession().sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * TODO: 发送者id和接收者id
     * 对某个用户发消息
     * @param groupId groupId
     * @param userId 接收者id
     * @param message message
     */
    @Override
    public void sendMessageById(String groupId, String userId, String message) {
        Map<String, WebSocketBean> cMap = WsSessionManager.get(groupId);
        if(cMap != null){
            WebSocketBean bean = cMap.get(userId);
            if(bean != null){
                try {
                    bean.getSession().sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
