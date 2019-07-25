package com.sun.library.base;

import com.sun.library.Setting;

import java.util.TreeMap;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：10:29
 * 说明：
 * 备注：
 * =============================+
 */
public class BaseBiz {

    /**
     * 获取基础request参数
     */
    public TreeMap<String, Object> getBaseRequest() {
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("key", Setting.BASE_KEY);
        return map;
    }
}
