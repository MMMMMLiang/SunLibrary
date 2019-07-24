package com.sun.library.http.net.load.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:37
 * 说明：通过Interceptor回调监听Response进度
 * 备注：
 * =============================+
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadProgressCallback callback;

    public DownloadInterceptor(DownloadProgressCallback callback) {
        this.callback = callback;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(), callback))
                .build();
    }
}
