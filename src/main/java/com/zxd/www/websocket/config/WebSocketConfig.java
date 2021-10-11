package com.zxd.www.websocket.config;

import com.zxd.www.websocket.handler.impl.HttpAuthHandler;
import com.zxd.www.websocket.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Makonike
 * @date 2021-10-10 10:34
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private HttpAuthHandler authHandler;

    @Autowired
    private WebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(authHandler, "/api/exam")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*");
    }
}
