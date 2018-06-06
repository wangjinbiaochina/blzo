package com.jdkhome.blzo.common.enums;

/**
 * Created by jdk on 17/4/24.
 * 错误枚举，错误码和错误信息定义到这里,注意，这是面向(视图层)用户的错误提示
 */
public enum ResponseError {



    //==============================================后台=======

    //==========管理员管理=====
    RESP_ERROR_ADMIN_NOT_EXIST(10001, "管理员不存在"),
    RESP_ERROR_PASSWORD_ERROR(10002, "管理员密码错误"),


    //===========通用返回==============
    NO_LOGIN(100, "请先登录"),
    SUCCESS(200, "成功"),
    PARAMETER_ERROR(400, "参数错误"),
    NO_PERMISSION(403, "没有权限"),
    SERVER_ERROR(500, "服务器未知错误");


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
