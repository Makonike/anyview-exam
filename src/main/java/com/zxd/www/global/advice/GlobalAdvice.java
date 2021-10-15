package com.zxd.www.global.advice;

import com.zxd.www.global.entity.dto.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * @author Makonike
 * @date 2021-10-15 22:23
 **/
@ControllerAdvice(basePackages = {"com.zxd.www"})
@ResponseBody
@Slf4j
public class GlobalAdvice {

    /**
     * 处理Shiro中的未授权异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public JsonResponse handleUnauthenticated(UnauthorizedException e){
        log.info(e.getMessage());
        return new JsonResponse().forbidden().message("权限不足");
    }

    /**
     * 默认的异常处理
     */
    @ExceptionHandler(Exception.class)
    public JsonResponse handleDefault(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new JsonResponse().internalServerError().message(e.getMessage());
    }
}
