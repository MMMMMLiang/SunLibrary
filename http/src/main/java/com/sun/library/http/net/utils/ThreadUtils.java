package com.sun.library.http.net.utils;

import android.os.Looper;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:04
 * 说明：线程工具类
 * 备注：
 * =============================+
 */
public class ThreadUtils {
    /**
     * 是否主线程
     *
     * @return boolean
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }
}
