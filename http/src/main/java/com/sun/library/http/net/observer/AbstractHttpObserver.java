package com.sun.library.http.net.observer;

import android.text.TextUtils;

import com.sun.library.http.net.cancel.RequestCancel;
import com.sun.library.http.net.cancel.RequestManagerImpl;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:24
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class AbstractHttpObserver<T> implements Observer<T>, RequestCancel {
    /**
     * 请求标识
     */
    private String mTag;

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!TextUtils.isEmpty(mTag)) {
            RequestManagerImpl.getInstance().remove(mTag);
        }
    }

    @Override
    public void onComplete() {
        /**
         * 由于LifecycleProvider取消监听直接截断事件发送，但是必定回调onComplete()
         * 因此在这里判断请求是否被取消，如果到这里还未被取消，说明是LifecycleProvider导致的取消请求，回调onCancel逻辑
         * 备注：
         * 1.子类重写此方法时需要调用super
         * 2.多个请求复用一个监听者HttpObserver时，tag会被覆盖，取消回调会有误
         */
        if (!RequestManagerImpl.getInstance().isDisposed(mTag)) {
            cancel();
        }
    }

    @Override
    public void onNext(@NonNull T value) {
        if (!TextUtils.isEmpty(mTag)) {
            RequestManagerImpl.getInstance().remove(mTag);
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(mTag)) {
            RequestManagerImpl.getInstance().add(mTag, d);
        }
    }

    /**
     * 手动取消请求
     */
    @Override
    public void cancel() {
        if (!TextUtils.isEmpty(mTag)) {
            RequestManagerImpl.getInstance().cancel(mTag);
        }
    }

    /**
     * 是否已经处理
     *
     * @author ZhongDaFeng
     */
    public boolean isDisposed() {
        if (TextUtils.isEmpty(mTag)) {
            return true;
        }
        return RequestManagerImpl.getInstance().isDisposed(mTag);
    }

    /**
     * 设置标识请求的TAG
     *
     * @param tag
     */
    public void setTag(String tag) {
        this.mTag = tag;
    }


}
