package com.zxd.www.user.filter;

import com.zxd.www.global.constant.JwtConstant;
import com.zxd.www.user.entity.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * user鉴权登录拦截器
 * @author Makonike
 * @date 2021-10-12 19:36
 **/
@Slf4j
public class UserFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     * @param request request
     * @param response response
     * @param mappedValue mappedValue
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            try {
                // token失效，重定向到未授权接口
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                String message = "Token失效请重新登录";
                message = URLEncoder.encode(message, "UTF-8");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                httpServletResponse.sendRedirect("/user/unauthorized/" + message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // token失效跳转接口，不返回false
        return false;
    }

    /**
     * 执行登录
     * @param request request
     * @param response response
     * @throws Exception exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(JwtConstant.USER_TOKEN_HEADER_KEY);

        UserToken jwtToken = new UserToken(token);
        // 提交给realm进行登录，如果错误则会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没抛出异常则登录成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     * @param request request
     * @param response response
     * @throws Exception exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
