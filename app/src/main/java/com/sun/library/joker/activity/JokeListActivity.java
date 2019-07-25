package com.sun.library.joker.activity;

import android.widget.TextView;

import com.sun.library.R;
import com.sun.library.base.BaseActivity;
import com.sun.library.bean.ResultData;
import com.sun.library.http.mvp.root.IMvpPresenter;
import com.sun.library.joker.contract.JokeContract;
import com.sun.library.joker.presenter.JokeListPresenter;
import com.trello.rxlifecycle3.LifecycleProvider;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：12:04
 * 说明：
 * 备注：
 * =============================+
 */
public class JokeListActivity extends BaseActivity implements JokeContract.IJokeListView {
    private JokeListPresenter presenter = new JokeListPresenter();

    private TextView tv;

    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[]{presenter};
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_joke_list;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        tv = findViewById(R.id.content);

    }

    @Override
    protected void initData() {
        presenter.getData("1418816972", "desc", 1, 20);
    }

    @Override
    public void showResult(ResultData data) {
        tv.setText(data.getData().get(0).getContent());
    }

    @Override
    public void showError(int code, String msg) {
        tv.setText(code+":"+msg);
    }

    @Override
    public void showEmpty() {
        tv.setText("empty");
    }

    @Override
    public LifecycleProvider getRxLifecycle() {
        return this;
    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void dismissProgressView() {

    }
}
