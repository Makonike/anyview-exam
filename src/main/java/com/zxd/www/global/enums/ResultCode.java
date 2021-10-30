package com.zxd.www.global.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 状态码
 * @author Makonike
 * @date 2021-09-15 10:45
 */
public enum ResultCode {

    /**
     * 请求成功，不管什么http方法
     */
    OK("200"),
    /**
     * 服务器不能解析客户端的请求
     */
    BAD_REQUEST("400"),
    /**
     * 用户未验证身份（即未登录）
     */
    UNAUTHORIZED("401"),
    /**
     * 用户不具有该资源访问权限（已登录）
     */
    FORBIDDEN("403"),
    /**
     * 所请求资源不存在或不可用（临时）
     */
    NOT_FOUND("404"),
    /**
     * 所请求资源不存在或不可用（永久）
     */
    GONE("410"),
    /**
     * 客户端上传的附件无法处理
     */
    UNPROCESSABLE_ENTITY("422"),
    /**
     * 客户端的请求次数超过限额
     */
    TOO_MANY_REQUEST("429"),
    /**
     * 客户端请求有效，服务器处理时发生了意外
     */
    INTERNAL_SERVER_ERROR("500");

    @JsonValue
    String code;

    ResultCode(String code) {
        this.code = code;
    }
}
