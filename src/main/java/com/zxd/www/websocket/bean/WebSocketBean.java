package com.zxd.www.websocket.bean;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 将websocket session封装到bean中
 * 同时可以记录session的连接错误次数
 * @author Makonike
 * @date 2021-10-10 10:56
 **/
@Data
public class WebSocketBean {

    /**
     * 连接session对象
     */
    private WebSocketSession session;

    /**
     * 连接错误次数
     */
    private AtomicInteger errorLinkCount = new AtomicInteger(0);

    /**
     * 获取错误计数并自增
     * @return 自增前的值
     */
    public int getErrorLinkCount(){
        // 以原子方式+1，并返回自增前的值
        return errorLinkCount.getAndIncrement();
    }

    /**
     * 清空错误计数
     */
    public void cleanErrorNum(){
        errorLinkCount = new AtomicInteger(0);
    }
}
