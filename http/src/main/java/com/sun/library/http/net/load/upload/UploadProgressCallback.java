package com.sun.library.http.net.load.upload;

import java.io.File;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:46
 * 说明：进度回调接口
 * 备注：
 * =============================+
 */
public interface UploadProgressCallback {

    /**
     * 上传进度回调
     *
     * @param currentSize  当前值
     * @param totalSize    总大小
     * @param progress     进度
     * @param currentIndex 当前下标
     * @param totalFile    总文件数
     */
    void progress(File file, long currentSize, long totalSize, float progress, int currentIndex, int totalFile);
}
