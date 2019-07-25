package com.sun.library.joker.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.library.base.BizFactory;
import com.sun.library.base.callback.BaseHttpCallback;
import com.sun.library.bean.ResultData;
import com.sun.library.http.mvp.model.ModelCallback;
import com.sun.library.joker.biz.JokeBiz;
import com.sun.library.joker.contract.JokeContract;
import com.trello.rxlifecycle3.LifecycleProvider;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:52
 * 说明：
 * 备注：
 * =============================+
 */
public class JokeListModel implements JokeContract.IJokeListModel {
    @Override
    public void getJokeListData(Context context, String time, String sort, int page, int pagesize,
                                LifecycleProvider lifecycle, final ModelCallback.Http<ResultData> modelCallback) {
        BizFactory.getBiz(JokeBiz.class).getJokeList(time, sort, page, pagesize, lifecycle, new BaseHttpCallback<ResultData>() {
            @Override
            public ResultData convert(JsonElement data) {
                return new Gson().fromJson(data, ResultData.class);
            }

            @Override
            public void onSuccess(ResultData value) {
                modelCallback.onSuccess(value);
            }

            @Override
            public void onError(int code, String desc) {
                modelCallback.onError(code, desc);
            }

            @Override
            public void onCancel() {
                modelCallback.onCancel();
            }
        });
    }
}
