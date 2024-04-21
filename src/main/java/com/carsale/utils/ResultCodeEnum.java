package com.carsale.utils;

public enum ResultCodeEnum {
    Request_successful(200,"请求成功"),
    requested_resource_no_modified(304,"请求的资源未被修改"),
    request_syntaxerrors_or_invalidparameters(400,"请求存在语法错误或无效参数"),
    Invalid_authentication_token(401,"无效的身份验证令牌"),
    Insufficient_permissions(403,"权限不足以执行此操作"),
    no_Resource(404,"资源不存在"),
    Request_failed(422,"请求参数验证失败"),
    Server_error(500,"服务器出现错误"),
    Server_overload(503,"服务器过载或维护中"),
    ;


    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
