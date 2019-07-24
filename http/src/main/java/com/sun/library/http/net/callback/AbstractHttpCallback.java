package com.sun.library.http.net.callback;

import com.sun.library.http.net.exception.ExceptionEngine;
import com.sun.library.http.net.helper.ParseHelper;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:34
 * 说明：Http请求回调
 * 备注：
 * =============================+
 */
public abstract class AbstractHttpCallback<T> extends AbstractBaseCallback<T> implements ParseHelper<T> {
    /**
     * 是否回调成功函数
     */
    private boolean callSuccess = true;

    @Override
    public T parse(String data) {
        T t = null;
        try {
            t = onConvert(data);
            callSuccess = true;
        } catch (Exception e) {
            callSuccess = false;
            e.printStackTrace();
            onError(ExceptionEngine.ANALYTIC_CLIENT_DATA_ERROR, "解析数据出错");
        }
        return t;
    }


    @Override
    public void inSuccess(T value) {
        T result = parse((String) value);
        if (callSuccess && isBusinessOk()) {
            onSuccess(result);
        }
    }

    @Override
    public void inError(int code, String desc) {
        onError(code, desc);
    }

    @Override
    public void inCancel() {
        onCancel();
    }

    /**
     * 数据转换/解析数据
     *
     * @param data
     * @return
     */
    public abstract T onConvert(String data) throws Exception;

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
    public abstract boolean isBusinessOk();
}
