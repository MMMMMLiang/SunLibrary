package com.sun.library.joker.biz;

import com.sun.library.base.BaseBiz;
import com.sun.library.contants.UrlContants;
import com.sun.library.http.net.SunHttp;
import com.sun.library.http.net.callback.AbstractHttpCallback;
import com.trello.rxlifecycle3.LifecycleProvider;

import java.util.TreeMap;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/25
 * 时间：11:41
 * 说明：
 * 备注：
 * =============================+
 */
public class JokeBiz extends BaseBiz {

    public void getJokeList(String time, String sort, int page, int pagesize, LifecycleProvider lifecycle, AbstractHttpCallback callback){
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("time", time);
        request.put("sort", sort);
        request.put("page", page);
        request.put("pagesize", pagesize);
        request.putAll(getBaseRequest());

        /**
         * 发送请求
         */
        SunHttp http = new SunHttp.Builder()
                .get()
                .apiUrl(UrlContants.URL_JOKE_LIST)
                .addParameter(request)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }
}
