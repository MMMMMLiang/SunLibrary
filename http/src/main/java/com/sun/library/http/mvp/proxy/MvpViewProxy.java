package com.sun.library.http.mvp.proxy;

import com.sun.library.http.mvp.root.IMvpView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:26
 * 说明：MvpView 代理
 * 备注：MvpView 对象需要在 activity/fragment 组件销毁时清空，目的是异步回调时不再处理 MvpView 的方法
 *       原理：通过代理，将 MvpView 的方法调用放在代理类中实现，通过判断代理中 MvpView 是否为空判断是否需要回调方法，
 *       同时避免 P 中每次调用  MvpView 都要判空的麻烦
 * =============================+
 */
public class MvpViewProxy<V extends IMvpView> implements InvocationHandler {
    private V mView;

    /**
     * 创建代理（接受委托）
     * @param view  view
     * @return 代理
     */
    public Object newProxyInstance(V view){
        this.mView = view;

        return Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        // V 为空直接返回 null 不再继续调用函数
        if (mView == null) {
            return null;
        }
        //调用目标方法并返回
        return method.invoke(mView, objects);
    }

    /**
     * 解绑View
     */
    public void detachView() {
        mView = null;
    }
}
