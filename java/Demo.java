package com.bot.demo;

import com.bot.demo.util.OkHttpUtil;
import com.bot.demo.util.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class Demo {
    private static Log loger = LogFactory.getLog(AuditDemo.class);

    private static final String API_URL = "https://api.botsmart.cn/baby/check";
    private static final String APP_ID = "API接入Key";
    private static final String APP_SECRET = "API接入Key";
    private static final String BUSINESS_ID = "";


    public static void main(String[] args) {
        SortedMap<String, String> params = new TreeMap<>();
        params.put("business_id", BUSINESS_ID);
        params.put("app_id", APP_ID);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("unique_id", UUID.randomUUID().toString());
        params.put("data", "待检测内容");
        String serverSignature = SignUtil.genSignature(APP_SECRET, params);
        params.put("signature", serverSignature);
        String str = OkHttpUtil.post(API_URL, params);
        System.out.println(str);
    }
}