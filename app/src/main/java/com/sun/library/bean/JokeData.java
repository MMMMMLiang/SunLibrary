package com.sun.library.bean;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:33
 * 说明：
 * 备注：
 * =============================+
 */
public class JokeData {
    private long unixtime;
    private String content, hashId, updatetime;

    public JokeData() {
    }

    public long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(long unixtime) {
        this.unixtime = unixtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
