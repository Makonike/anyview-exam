package com.zxd.www.global.entity.dto;

import com.zxd.www.global.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据传输类
 * @author Makonike
 * @date 2021-09-15 10:58
 */
@Data
@AllArgsConstructor
public class JsonResponse {

    /**
     * 状态码
     */
    private ResultCode code;

    /**
     * 回传信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 默认状态为ok
     */
    public JsonResponse() {
        this.code = ResultCode.OK;
    }


    /**
     * 设置自定义状态码
     * @param code code
     * @return JsonResponse
     */
    public JsonResponse code(ResultCode code) {
        this.code = code;
        return this;
    }


    /**
     * 设置状态码
     */
    public JsonResponse badRequest() {
        this.code = ResultCode.BAD_REQUEST;
        return this;
    }

    public JsonResponse unauthorized() {
        this.code = ResultCode.UNAUTHORIZED;
        return this;
    }

    public JsonResponse forbidden() {
        this.code = ResultCode.FORBIDDEN;
        return this;
    }

    public JsonResponse notFound() {
        this.code = ResultCode.NOT_FOUND;
        return this;
    }

    public JsonResponse gone() {
        this.code = ResultCode.GONE;
        return this;
    }

    public JsonResponse unprocessableEntity() {
        this.code = ResultCode.UNPROCESSABLE_ENTITY;
        return this;
    }

    public JsonResponse tooManyRequest() {
        this.code = ResultCode.TOO_MANY_REQUEST;
        return this;
    }

    public JsonResponse internalServerError() {
        this.code = ResultCode.INTERNAL_SERVER_ERROR;
        return this;
    }


    /**
     * 设置信息
     * @param message message
     */
    public JsonResponse message(String message) {
        this.message = message;
        return this;
    }


    public JsonResponse data(Object data) {
        this.data = data;
        return this;
    }
}
