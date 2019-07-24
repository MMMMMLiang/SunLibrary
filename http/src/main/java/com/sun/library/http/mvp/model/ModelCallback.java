package com.sun.library.http.mvp.model;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:31
 * 说明：模块数据回调接口
 * 备注：
 * =============================+
 */
public interface ModelCallback {
    /**
     * 网络数据回调，泛指http
     *
     * @param <T>
     */
    interface Http<T> {
        void onSuccess(T object);
        void onError(int code, String desc);
        void onCancel();
    }

    /**
     * 其他数据回调<本地数据，数据库等>
     *
     * @param <T>
     */
    interface Data<T> {
        void onSuccess(T object);
    }
}
