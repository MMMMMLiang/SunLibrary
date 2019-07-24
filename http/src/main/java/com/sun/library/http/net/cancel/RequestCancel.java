package com.sun.library.http.net.cancel;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:06
 * 说明：请求取消接口
 * 备注：
 * =============================+
 */
public interface RequestCancel {
    /**
     * 取消请求
     */
    void cancel();

    /**
     * 请求被取消
     */
    void onCanceled();
}
