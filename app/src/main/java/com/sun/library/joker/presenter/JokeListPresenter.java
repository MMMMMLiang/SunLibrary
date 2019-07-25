package com.sun.library.joker.presenter;

import com.sun.library.bean.ResultData;
import com.sun.library.http.mvp.AbstractMvpPresenter;
import com.sun.library.http.mvp.model.ModelCallback;
import com.sun.library.http.mvp.model.ModelFactory;
import com.sun.library.joker.contract.JokeContract;
import com.sun.library.joker.model.JokeListModel;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:57
 * 说明：
 * 备注：
 * =============================+
 */
public class JokeListPresenter extends AbstractMvpPresenter<JokeContract.IJokeListView> {

    public void getData(String time, String sort, int page, int pagesize){
        getView().showProgressView();

        ModelFactory.getModel(JokeListModel.class).getJokeListData(getView().getActivity(), time, sort, page, pagesize,
                getView().getRxLifecycle(), new ModelCallback.Http<ResultData>() {
                    @Override
                    public void onSuccess(ResultData object) {
                        getView().dismissProgressView();
                        getView().showResult(object);
                    }

                    @Override
                    public void onError(int code, String desc) {
                        getView().showError(code, desc);
                    }

                    @Override
                    public void onCancel() {
                        getView().dismissProgressView();
                    }
                });
    }

    @Override
    public void destroy() {

    }
}
