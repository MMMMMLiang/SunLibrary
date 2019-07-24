package com.sun.library.http.net.helper;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:16
 * 说明：数据解析helper
 * 备注：
 * =============================+
 */
public interface ParseHelper<T> {
    /**
     * 解析数据
     * @param data data
     * @return T
     */
    T parse(String data);
}
