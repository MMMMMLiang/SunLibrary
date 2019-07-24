package com.sun.library.http.mvp;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.sun.library.http.mvp.delegate.ActivityMvpDelegate;
import com.sun.library.http.mvp.delegate.ActivityMvpDelegateImpl;
import com.sun.library.http.mvp.delegate.MvpDelegateCallback;
import com.sun.library.http.mvp.root.IMvpPresenter;
import com.sun.library.http.mvp.root.IMvpView;
import com.trello.rxlifecycle3.components.support.RxFragmentActivity;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:54
 * 说明：
 * 备注：
 * =============================+
 */
public abstract class AbstractMvpFragmentActivity<V extends IMvpView, P extends IMvpPresenter<V>> extends RxFragmentActivity
        implements IMvpView, MvpDelegateCallback<V, P> {
    protected ActivityMvpDelegate mvpDelegate;

    /**
     * 获取 Presenter 数组
     * @return P[]
     */
    protected abstract P[] getPresenterArray();

    @Override
    public P[] getPresenter() {
        return getPresenterArray();
    }

    @Override
    public V[] getMvpView() {
        V[] view = null;
        P[] pArray = getPresenter();
        if (pArray != null) {
            view = (V[]) new IMvpView[pArray.length];
            for (int i = 0; i < pArray.length; i++) {
                view[i] = (V) this;
            }
        }
        return view;
    }

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this, this);
        }
        return mvpDelegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }
}
