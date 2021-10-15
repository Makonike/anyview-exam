package com.zxd.www.websocket.interceptor;

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
    private ExamService examService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        log.info("===开始握手===");
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        HttpServletRequest servletRequest = req.getServletRequest();

        // 子协议
        String token = servletRequest.getHeader("Sec-WebSocket-Protocol");
        log.info(token);
        Integer userId = JwtUtil.getUserId(token);

        // 如果是用户，就取出exam号存入groupId，将userId存入userId中, 取出的groupId为examNo，string
        if(userId != null){
            attributes.put(WebSocketConstant.USER_ID, userId.toString());
            attributes.put(WebSocketConstant.GROUP_ID, examService.getExamNoByUserId(userId));
            return true;
        }
        // 如果是管理员，就取出exam号存入groupId，并将groupId存入userId中
        Integer adminId = AdminJwtUtil.getAdminId(token);
        if(adminId != null){
            String groupId = examService.getExamNoByAdminId(adminId);
            attributes.put(WebSocketConstant.USER_ID, groupId);
            attributes.put(WebSocketConstant.GROUP_ID, groupId);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("===握手完成===");
    }
}
