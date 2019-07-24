package com.sun.library.http.net.utils;

import android.util.Log;

import com.sun.library.http.net.SunHttp;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:01
 * 说明：
 * 备注：
 * =============================+
 */
public class HttpLogUtils {
    private static final String TAG = "RHttp";
    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;

    static {
        allowD = allowE = allowI = allowV = allowW = SunHttp.Configure.get().isShowLog();
    }

    private HttpLogUtils() {
    }

    /**
     * 开启Log
     *
     * @author ZhongDaFeng
     */
    public static void openLog(boolean flag) {
        allowD = flag;
        allowE = flag;
        allowI = flag;
        allowV = flag;
        allowW = flag;
    }

    public static void d(String content) {
        if (!allowD) {
            return;
        }
        Log.d(TAG, content);
    }

    public static void e(String content) {
        if (!allowE) {
            return;
        }
        Log.e(TAG, content);
    }

    public static void i(String content) {
        if (!allowI) {
            return;
        }
        Log.i(TAG, content);
    }

    public static void v(String content) {
        if (!allowV) {
            return;
        }
        Log.v(TAG, content);
    }

    public static void w(String content) {
        if (!allowW) {
            return;
        }
        Log.w(TAG, content);
    }
}
