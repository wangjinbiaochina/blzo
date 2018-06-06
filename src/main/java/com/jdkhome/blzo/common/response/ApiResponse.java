package com.jdkhome.blzo.common.response;


import com.jdkhome.blzo.common.enums.ResponseError;

/**
 * Created by jdk on 2017/4/6.
 */
public class ApiResponse {

    private int code;
    private String msg;
    private Object data;

    private static final int SUCCESS_CODE = 200;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResponse() {
        this.code = 0;
        this.msg = "";
        this.data = null;
    }

    public ApiResponse(int code) {
        this.code = code;
        this.msg = (code == SUCCESS_CODE) ? "success" : msg;
        this.data = null;
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 返回成功响应
     */
    public static ApiResponse successResponse(Object data) {

        ApiResponse response = new ApiResponse(SUCCESS_CODE);
        if (data != null) {
            response.data = data;
        } else {
            response.data = null;
        }
        return response;
    }

    /**
     * 返回成功响应
     */
    public static ApiResponse successResponse() {

        return successResponse(null);
    }

    /**
     * 返回指定错误类型的响应
     */
    public static ApiResponse responseWithRespError(ResponseError responseError) {
        ApiResponse response = new ApiResponse(SUCCESS_CODE);
        response.code = responseError.getCode();
        response.msg = responseError.getMsg();
        response.data = null;
        return response;
    }


    /**
     * 未登录响应
     */
    public static ApiResponse unLoginResponse() {
        return responseWithRespError(ResponseError.NO_LOGIN);
    }

}
