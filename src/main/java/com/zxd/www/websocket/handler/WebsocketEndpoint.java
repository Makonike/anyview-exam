package com.zxd.www.websocket.handler;

/**
 * @author Makonike
 * @date 2021-10-10 14:32
 **/
public interface WebsocketEndpoint {

    /**
     * 群发
     * 用于关闭测验或开启测验或其他
     * @param groupId groupId
     * @param message userId
     */
    void batchSendMessage(String groupId, String message);

    /**
     * 发送给对应用户，警告啥的
     * @param groupId groupId
     * @param userId userId
     * @param message message
     */
    void sendMessageById(String groupId, String userId, String message);

}
