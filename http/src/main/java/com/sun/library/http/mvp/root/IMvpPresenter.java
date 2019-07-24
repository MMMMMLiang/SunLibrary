package com.sun.library.http.mvp.root;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:24
 * 说明：根Presenter
 * 备注：
 * =============================+
 */
public interface IMvpPresenter<V extends IMvpView> {
    /**
     * 将 View 添加到当前 Presenter
     * @param view  view
     */
    @UiThread
    void attachView(@NonNull V view);

    /**
     * 将 View 从 Presenter 移除
     */
    @UiThread
    void detachView();

    /**
     * 销毁 V 实例
     */
    @UiThread
    void destroy();
}
