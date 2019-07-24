package com.sun.library.http.net.cancel;

import io.reactivex.disposables.Disposable;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:06
 * 说明：Http请求管理接口
 * 备注：
 * =============================+
 */
public interface RequestManager<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消全部
     */
    void cancelAll();
}
