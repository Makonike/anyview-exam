package com.zxd.www.websocket.interceptor;

import cn.hutool.http.HttpUtil;
import com.zxd.www.websocket.constant.WebSocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 自定义握手拦截器
 * 用来认证
 * @author Makonike
 * @date 2021-10-10 19:44
 **/
@Component
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        log.info("===开始握手===");
        Map<String, String> paramMap = HttpUtil.decodeParamMap(request.getURI().getQuery(), StandardCharsets.UTF_8);
        String userId = paramMap.get(WebSocketConstant.USER_ID);
        String groupId = paramMap.get(WebSocketConstant.GROUP_ID);
        attributes.put(WebSocketConstant.USER_ID, userId);
        attributes.put(WebSocketConstant.GROUP_ID, groupId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("===握手完成===");
    }
}
