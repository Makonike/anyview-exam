package com.zxd.www.websocket.bean;

import lombok.Data;

/**
 * 推送信息传输类
 * @author Makonike
 * @date 2021-10-10 12:53
 **/
@Data
public class SendMessageAll {
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 组别标识
     */
    private String groupId;
}
