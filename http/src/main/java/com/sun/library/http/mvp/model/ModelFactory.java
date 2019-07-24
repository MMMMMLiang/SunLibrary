package com.sun.library.http.mvp.model;

import androidx.annotation.NonNull;

import com.sun.library.http.mvp.root.IMvpModel;

import java.util.HashMap;
import java.util.Map;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:32
 * 说明：Model工厂类
 * 备注：
 * =============================+
 */
public class ModelFactory {
    /**
     * 全局存储Model
     */
    private static Map<String, IMvpModel> modelMap = new HashMap<>();

    /**
     * 获取model
     * 查询Map中是否存在model实例,不存在时动态创建
     *
     * @param cls 类
     * @param <T> model
     * @return T
     */
    public static <T extends IMvpModel> T getModel(@NonNull Class<T> cls) {
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
     * 反射获取Model
     *
     * @param className 包含完整路径的类名称 com.ruffian.cn.User
     * @return T
     */
    private static <T extends IMvpModel> T getModelReflex(@NonNull String className) {
        T result = null;
        try {
            result = (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
