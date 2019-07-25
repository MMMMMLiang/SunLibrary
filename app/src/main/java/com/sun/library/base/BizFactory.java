package com.sun.library.base;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：10:32
 * 说明：Biz工厂类
 * 备注：
 * =============================+
 */
public class BizFactory {
    /**
     * 全局存储Biz
     */
    private static Map<String, Object> modelMap = new HashMap<>();

    /**
     * 获取Biz
     * 查询Map中是否存在Biz实例,不存在时动态创建
     *
     * @param cls 类
     * @param <T> model
     * @return
     */
    public static <T> T getBiz(@NonNull Class<T> cls) {
        //类名
        String className = cls.getName();
        T model = (T) modelMap.get(className);
        //不存在
        if (model == null) {
            model = getModelReflex(className);
            modelMap.put(className, model);
        }
        return model;
    }

    /**
     * 反射获取Biz
     *
     * @param className 包含完整路径的类名称 com.ruffian.cn.UserBiz
     * @param <T>
     * @return
     */
    private static <T> T getModelReflex(@NonNull String className) {
        T result = null;
        try {
            result = (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
