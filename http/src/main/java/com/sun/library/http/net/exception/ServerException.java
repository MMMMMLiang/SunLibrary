package com.sun.library.http.net.exception;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:10
 * 说明：自定义服务器错误
 * 备注：
 * =============================+
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

