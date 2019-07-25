package com.sun.library.bean;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:24
 * 说明：
 * 备注：
 * =============================+
 */
public class ResultData {
    private List<JokeData> data;

    public ResultData() {
    }

    public List<JokeData> getData() {
        return data;
    }

    public void setData(List<JokeData> data) {
        this.data = data;
    }
}
