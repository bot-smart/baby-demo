package com.bot.demo.util;

import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Iterator;
import java.util.Map;

/**
 * httpClient Util
 *
 * @author cilong
 * @version 2020-04-16
 */
public class OkHttpUtil {
    private static Log loger = LogFactory.getLog(OkHttpUtil.class);

    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient.Builder()
                //.sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .retryOnConnectionFailure(false)
                .build();
    }
    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";

        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            loger.error("okhttp3 put error >> ex =" + e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
}
