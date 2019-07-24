package com.sun.library.http.net.cancel;

import android.annotation.TargetApi;
import android.os.Build;

import androidx.collection.ArrayMap;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:07
 * 说明：Http请求管理实现类
 * 备注：
 * =============================+
 */
public class RequestManagerImpl implements RequestManager<Object> {

    private static volatile RequestManagerImpl mInstance;
    /**
     * 处理,请求列表
     */
    private ArrayMap<Object, Disposable> mMaps;

    public static RequestManagerImpl getInstance() {
        if (mInstance == null) {
            synchronized (RequestManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new RequestManagerImpl();
                }
            }
        }
        return mInstance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private RequestManagerImpl() {
        mMaps = new ArrayMap<>();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(Object tag, Disposable disposable) {
        mMaps.put(tag, disposable);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(Object tag) {
        if (!mMaps.isEmpty()) {
            mMaps.remove(tag);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancel(Object tag) {
        if (mMaps.isEmpty()) {
            return;
        }
        if (mMaps.get(tag) == null) {
            return;
        }
        if (!mMaps.get(tag).isDisposed()) {
            mMaps.get(tag).dispose();
        }
        mMaps.remove(tag);
    }

    @Override
    public void cancelAll() {
        if (mMaps.isEmpty()) {
            return;
        }
        //遍历取消请求
        Disposable disposable;
        Set<Object> keySet = mMaps.keySet();
        for (Object key : keySet) {
            disposable = mMaps.get(key);
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        mMaps.clear();
    }


    /**
     * 判断是否取消了请求
     *
     * @param tag
     * @return
     */
    public boolean isDisposed(Object tag) {
        if (mMaps.isEmpty() || mMaps.get(tag) == null) {
            return true;
        }
        return mMaps.get(tag).isDisposed();
    }
}
