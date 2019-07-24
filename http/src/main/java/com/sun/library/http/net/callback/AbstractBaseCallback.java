package com.sun.library.http.net.callback;

import com.sun.library.http.net.SunHttp;
import com.sun.library.http.net.exception.ApiException;
import com.sun.library.http.net.exception.ExceptionEngine;
import com.sun.library.http.net.observer.AbstractHttpObserver;
import com.sun.library.http.net.utils.ThreadUtils;

import java.lang.reflect.ParameterizedType;

import io.reactivex.annotations.NonNull;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:33
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class AbstractBaseCallback<T> extends AbstractHttpObserver<T> {

    @Override
    public void onNext(@NonNull T value) {
        super.onNext(value);
        inSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            inError(exception.getCode(), exception.getMsg());
        } else {
            inError(ExceptionEngine.UN_KNOWN_ERROR, "未知错误");
        }
    }

    @Override
    public void onCanceled() {
        onCanceledLogic();
    }

    /**
     * 请求成功
     *
     * @param t
     */
    public abstract void inSuccess(T t);

    /**
     * 请求出错
     *
     * @param code
     * @param desc
     */
    public abstract void inError(int code, String desc);

    /**
     * 请求取消
     */
    public abstract void inCancel();

    /**
     * Http被取消回调处理逻辑
     */
    private void onCanceledLogic() {
        if (!ThreadUtils.isMainThread()) {
            SunHttp.Configure.get().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    inCancel();
                }
            });
        } else {
            inCancel();
        }
    }

    @Deprecated
    private void getTypeClass() {
        /**
         * 获取当前类泛型(暂时保留)
         */
        ParameterizedType ptClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> mClass;
        if (ptClass != null) {
            mClass = (Class<T>) ptClass.getActualTypeArguments()[0];
            //LogUtils.e("当前类泛型:" + mClass);
        }
    }

}
