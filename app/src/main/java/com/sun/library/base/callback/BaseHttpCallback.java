package com.sun.library.base.callback;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.library.bean.BaseResult;
import com.sun.library.http.net.callback.AbstractHttpCallback;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:21
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class BaseHttpCallback<T> extends AbstractHttpCallback<T> {
    private BaseResult result;

    @Override
    public T onConvert(String data) {
        /**
         * 接口响应数据格式如@Response
         * 根据业务封装:
         * 1. result.isSuccess() (code==0) 业务逻辑成功回调convert()=>onSuccess()，否则失败回调onError()
         * 2.统一处理接口逻辑 例如:code==101 token过期等等
         */
        T t = null;
        result = new Gson().fromJson(data, BaseResult.class);
        int code = result.getError_code();
        String msg = result.getReason();
        JsonElement resultData = result.getResult();
        switch (code) {
            case 101://token过期，跳转登录页面重新登录(示例)
                break;
            case 102://系统公告(示例)
                break;
            default:
                if (result.isSuccess()) {//与服务器约定成功逻辑
                    t = convert(resultData);
                } else {//统一为错误处理
                    onError(code, msg);
                }
                break;
        }
        return t;
    }

    /**
     * 数据转换/解析
     *
     * @param data
     * @return
     */
    public abstract T convert(JsonElement data);

    /**
     * 成功回调
     *
     * @param value
     */
    public abstract void onSuccess(T value);

    /**
     * 失败回调
     *
     * @param code
     * @param desc
     */
    public abstract void onError(int code, String desc);

    /**
     * 取消回调
     */
    public abstract void onCancel();

    /**
     * 业务逻辑是否成功
     *
     * @return
     */
    @Override
    public boolean isBusinessOk() {
        return result.isSuccess();
    }
}
