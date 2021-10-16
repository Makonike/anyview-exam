package com.zxd.www.websocket.interceptor;

import com.zxd.www.clazz.service.ClassService;
import com.zxd.www.exam.service.ExamService;
import com.zxd.www.sys.util.AdminJwtUtil;
import com.zxd.www.user.util.JwtUtil;
import com.zxd.www.websocket.constant.WebSocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ClassService classService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        HttpServletRequest servletRequest = req.getServletRequest();

        // 子协议
        String token = servletRequest.getHeader("Sec-WebSocket-Protocol");
        log.info(token);
        Integer userId = JwtUtil.getUserId(token);

        // 如果是用户，就取出班级号存入groupId，将userId存入userId中
        if(userId != null){
            attributes.put(WebSocketConstant.USER_ID, userId.toString());
            attributes.put(WebSocketConstant.GROUP_ID, classService.getClassIdByUserId(userId).toString());
            return true;
        }

        // 如果是管理员，就将管理员放到专属管理员group里，并将adminId存入userId中
        Integer adminId = AdminJwtUtil.getAdminId(token);
        if(adminId != null){
            attributes.put(WebSocketConstant.USER_ID, adminId.toString());
            attributes.put(WebSocketConstant.GROUP_ID, WebSocketConstant.ADMIN_GROUP_ID);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
