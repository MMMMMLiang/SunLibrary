package com.sun.library.http.mvp;

import android.app.Activity;

import androidx.annotation.UiThread;

import com.sun.library.http.lifecycle.LifecycleProvider;
import com.sun.library.http.mvp.root.IMvpView;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:45
 * 说明：基础View接口
 * 备注：
 * =============================+
 */
public interface MvpView extends IMvpView {

    /**
     * RxLifecycle用于绑定组件生命周期
     *
     * @return
     */
    LifecycleProvider getRxLifecycle();

    /**
     * 获取Activity实例
     *
     * @return
     */
    Activity getActivity();

    /**
     * 显示进度View
     */
    @UiThread
    void showProgressView();

    /**
     * 隐藏进度View
     */
    @UiThread
    void dismissProgressView();
}
