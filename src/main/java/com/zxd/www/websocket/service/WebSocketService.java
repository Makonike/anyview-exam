package com.zxd.www.websocket.service;

/**
 *
 * @author Makonike
 * @date 2021-10-10 17:23
 **/
public interface WebSocketService {

    /**
     * 像所有在线用户群发消息
     * @param groupId 组id
     * @param message 消息
     */
    void sendMessageAll(String groupId, String message);

    /**
     * 发送给特定用户
     * @param groupId 组id
     * @param userId 用户id
     * @param message 消息
     */
    void sendMessageById(String groupId, String userId, String message);
}
