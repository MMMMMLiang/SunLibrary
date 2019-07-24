package com.sun.library.http.net.load.download;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:44
 * 说明：下载回调接口
 * 备注：
 * =============================+
 */
public interface DownloadProgressCallback {
    /**
     * 下载进度回调
     *
     * @param currentSize 当前值
     * @param totalSize   总大小
     */
    void progress(long currentSize, long totalSize);
}
