package com.sun.library.http.net.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * =============================+
 *
 * @author Sun
 * 日期：2019/7/24
 * 时间：21:02
 * 说明：请求工具类
 * 备注：
 * =============================+
 */
public class RequestUtils {
    private static RequestUtils instance = null;

    private RequestUtils() {
    }

    public static RequestUtils get() {
        if (instance == null) {
            synchronized (RequestUtils.class) {
                if (instance == null) {
                    instance = new RequestUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 获取BaseUrl
     * 备注:根据完整URL获取BasUrl
     *
     * @param url url
     * @return String
     */
    public static String getBasUrl(String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }


    /**
     * 获取 encode 后 Header 值
     * 备注: OkHttp Header 中的 value 不支持 null, \n 和 中文 等特殊字符
     * 后台解析中文 Header 值需要decode（这个后台处理，前端不用理会）
     *
     * @param value value
     * @return Object
     */
    public static Object getHeaderValueEncoded(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            //换行符
            String strValue = ((String) value).replace("\n", "");
            for (int i = 0, length = strValue.length(); i < length; i++) {
                char c = strValue.charAt(i);
                if (c <= '\u001f' || c >= '\u007f') {
                    try {
                        //中文处理
                        return URLEncoder.encode(strValue, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            }
            return strValue;
        } else {
            return value;
        }
    }
}
