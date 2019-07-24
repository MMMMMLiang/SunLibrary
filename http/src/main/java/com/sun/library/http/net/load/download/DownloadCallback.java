package com.sun.library.http.net.load.download;

import com.sun.library.http.net.model.Download;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:36
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class DownloadCallback<T extends Download> {

    /**
     * 进度回调
     *
     * @param state       下载状态
     * @param currentSize 当前已下载
     * @param totalSize   文件总大小
     * @param progress    进度
     */
    public abstract void onProgress(Download.State state, long currentSize, long totalSize, float progress);

    /**
     * 下载出错
     *
     * @param e
     */
    public abstract void onError(Throwable e);

    /**
     * 下载成功
     *
     * @param object
     */
    public abstract void onSuccess(T object);
}
