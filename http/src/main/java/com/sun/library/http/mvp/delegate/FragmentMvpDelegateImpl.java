package com.sun.library.http.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sun.library.http.mvp.root.IMvpPresenter;
import com.sun.library.http.mvp.root.IMvpView;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/11
 * 时间：16:42
 * 说明：
 * 备注：
 * =============================+
 */
public class FragmentMvpDelegateImpl<V extends IMvpView, P extends IMvpPresenter<V>> implements FragmentMvpDelegate {
    /**
     * Fragment
     */
    protected Fragment fragment;

    /**
     * V & P
     */
    private MvpDelegateCallback<V, P> delegateCallback;

    public FragmentMvpDelegateImpl(Fragment fragment, MvpDelegateCallback<V, P> delegateCallback) {
        if (fragment == null) {
            throw new NullPointerException("Fragment is null!");
        }
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.fragment = fragment;
        this.delegateCallback = delegateCallback;
    }

    /**
     * 是否保留V&P实例
     *
     * @return boolean
     */
    private static boolean retainVPInstance(Activity activity, Fragment fragment) {
        if (activity.isChangingConfigurations()) {
            return false;
        }
        if (activity.isFinishing()) {
            return false;
        }
        return !fragment.isRemoving();
    }

    /**
     * 获取Activity
     *
     * @return Activity
     */
    private Activity getActivity() {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity returned by Fragment.getActivity() is null. Fragment is " + fragment);
        }
        return activity;
    }

    @Override
    public void onCreate(Bundle saved) {
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            V[] vArray = delegateCallback.getMvpView();
            P p;
            V v;
            for (int i = 0; i < pArray.length; i++) {
                p = pArray[i];
                v = vArray[i];
                if (p != null && v != null) {
                    //关联view
                    p.attachView(v);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Activity activity = getActivity();
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            P presenter;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                if (presenter != null) {
                    if (!retainVPInstance(activity, fragment)) {
                        //销毁 V & P 实例
                        presenter.destroy();
                    }
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onDestroyView() {
        P[] pArray = delegateCallback.getPresenter();
        if (pArray != null) {
            P presenter;
            for (int i = 0; i < pArray.length; i++) {
                presenter = pArray[i];
                if (presenter != null) {
                    //解除View
                    presenter.detachView();
                }
            }
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
