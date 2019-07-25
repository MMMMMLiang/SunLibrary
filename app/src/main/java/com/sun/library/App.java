package com.sun.library;

import android.app.Application;

import com.sun.library.http.net.SunHttp;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：10:01
 * 说明：
 * 备注：
 * =============================+
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initHttp();
    }

    private void initHttp(){
        SunHttp.Configure.get()
                .baseUrl(Setting.BASE_URL)
                .init(this);
    }
}
