package com.sun.library.bean;

import com.google.gson.JsonElement;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:22
 * 说明：
 * 备注：
 * =============================+
 */
public class BaseResult {
    private int error_code;
    private String reason;
    private JsonElement result;

    public BaseResult() {
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JsonElement getResult() {
        return result;
    }

    public void setResult(JsonElement result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return error_code == 0;
    }
}
