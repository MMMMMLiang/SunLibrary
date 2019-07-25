package com.sun.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.sun.library.http.mvp.AbstractMvpAppCompatActivity;
import com.sun.library.http.mvp.root.IMvpPresenter;


/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：10:33
 * 说明：
 * 备注：设计图尺寸 750*1334
 * =============================+
 */
public abstract class BaseActivity extends AbstractMvpAppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        mContext = this;

        initBundleData();
        initView();
        initData();
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();


    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化Data
     */
    protected abstract void initData();

    /**
     * Presenter绑定入口，组件使用Presenter时存入具体值
     */
    @Override
    protected IMvpPresenter[] getPresenterArray() {
        return new IMvpPresenter[0];
    }

    /**
     * 获取Activity实例
     */
    public Activity getActivity() {
        return this;
    }
}
