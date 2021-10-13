package com.zxd.www.websocket.filter;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Makonike
 * @date 2021-10-13 18:54
 **/
@Order(1)
@Component
@WebServlet(name = "webSocketFilter", urlPatterns = "/api/exam")
public class WebSocketFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = ((HttpServletRequest) servletRequest).getHeader("Sec-WebSocket-Protocol");
        if(token != null){
            response.setHeader("Sec-WebSocket-Protocol",token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
