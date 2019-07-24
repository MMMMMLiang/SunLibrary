package com.sun.library.http.net.model;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.sun.library.http.net.api.Api;
import com.sun.library.http.net.load.download.DownloadCallback;

import java.io.Serializable;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:18
 * 说明：
 * 备注：
 * =============================+
 */
@Table("download")
public class Download implements Serializable {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    private long id;

    /**
     * 本地存储地址
     */
    @Column("localUrl")
    private String localUrl;

    /**
     * 下载地址
     */
    @Column("serverUrl")
    private String serverUrl;

    /**
     * 文件大小
     */
    @Column("totalSize")
    private long totalSize;

    /**
     * 当前大小
     */
    @Column("currentSize")
    private long currentSize;

    /**
     * 下载状态
     */
    @Column("state")
    private State state = State.NONE;

    /**
     * 接口service
     */
    @Ignore
    private Api api;

    /**
     * 回调接口
     */
    @Ignore
    private DownloadCallback callback;

    public Download() {
    }

    public Download(String url) {
        setServerUrl(url);
    }

    public Download(String url, DownloadCallback callback) {
        setServerUrl(url);
        setCallback(callback);
    }

    /**
     * 枚举下载状态
     */
    public enum State {
        /**
         * 无状态
         */
        NONE,
        /**
         * 等待
         */
        WAITING,
        /**
         * 下载中
         */
        LOADING,
        /**
         * 暂停
         */
        PAUSE,
        /**
         * 错误
         */
        ERROR,
        /**
         * 完成
         */
        FINISH,
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalUrl() {
        return localUrl == null ? "" : localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getServerUrl() {
        return serverUrl == null ? "" : serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public DownloadCallback getCallback() {
        return callback;
    }

    public void setCallback(DownloadCallback callback) {
        this.callback = callback;
    }
}
