package com.sun.library.http.mvp;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.sun.library.http.mvp.proxy.MvpViewProxy;
import com.sun.library.http.mvp.root.IMvpPresenter;
import com.sun.library.http.mvp.root.IMvpView;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:47
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class AbstractMvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {
    protected V mView;

    /**
     *  View代理对象
     */
    protected MvpViewProxy<V> mMvpViewProxy;

    /**
     * 获取view
     *
     * @return view
     */
    @UiThread
    public V getView() {
        return mView;
    }

    /**
     * 判断View是否已经添加
     *
     * @return boolean
     */
    @UiThread
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 绑定View
     *
     * @param view view
     */
    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        mMvpViewProxy = new MvpViewProxy<V>();
        mView = (V) mMvpViewProxy.newProxyInstance(view);
    }

    /**
     * 移除View
     */
    @Override
    public void detachView() {
        if (mMvpViewProxy != null) {
            mMvpViewProxy.detachView();
        }
    }
}
