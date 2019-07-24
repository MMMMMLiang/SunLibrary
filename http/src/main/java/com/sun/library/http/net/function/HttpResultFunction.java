package com.sun.library.http.net.function;

import com.sun.library.http.net.exception.ExceptionEngine;
import com.sun.library.http.net.utils.HttpLogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:13
 * 说明：http结果处理函数
 * 备注：
 * =============================+
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        HttpLogUtils.e("HttpResultFunction:" + throwable);
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
