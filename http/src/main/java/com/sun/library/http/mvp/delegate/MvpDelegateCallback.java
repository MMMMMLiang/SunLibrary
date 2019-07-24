package com.sun.library.http.mvp.delegate;

import com.sun.library.http.mvp.root.IMvpPresenter;
import com.sun.library.http.mvp.root.IMvpView;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:35
 * 说明：V/P 媒介
 * 备注：
 * =============================+
 */
public interface MvpDelegateCallback<V extends IMvpView, P extends IMvpPresenter<V>> {

    /**
     * 获取 Presenter
     * @return Presenter
     */
    P[] getPresenter();

    /**
     * 获取 View
     * @return View
     */
    V[] getMvpView();
}
