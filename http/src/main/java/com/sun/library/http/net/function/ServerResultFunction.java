package com.sun.library.http.net.function;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.library.http.net.utils.HttpLogUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:15
 * 说明：服务器结果处理函数
 * 备注：
 * =============================+
 */
public class ServerResultFunction implements Function<JsonElement, Object> {
    @Override
    public Object apply(@NonNull JsonElement response) throws Exception {
        //打印服务器回传结果
        HttpLogUtils.e("HttpResponse:" + response.toString());
        /*此处不再处理业务相关逻辑交由开发者重写httpCallback*/
        return new Gson().toJson(response);
    }
}
