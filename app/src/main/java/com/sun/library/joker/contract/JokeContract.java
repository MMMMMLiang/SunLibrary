package com.sun.library.joker.contract;

import android.content.Context;

import com.sun.library.bean.ResultData;
import com.sun.library.http.lifecycle.LifecycleProvider;
import com.sun.library.http.mvp.MvpView;
import com.sun.library.http.mvp.model.ModelCallback;
import com.sun.library.http.mvp.root.IMvpModel;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:46
 * 说明：
 * 备注：
 * =============================+
 */
public interface JokeContract {
    interface IJokeListView extends MvpView{
        void showResult(ResultData data);

        void showError(int code, String msg);

        void showEmpty();
    }

    interface IJokeListModel extends IMvpModel{
        void getJokeListData(final Context context, String time, String sort, int page, int pagesize,
                             LifecycleProvider lifecycle, ModelCallback.Http<ResultData> modelCallback);
    }
}
