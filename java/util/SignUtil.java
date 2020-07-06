package com.bot.demo.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.SortedMap;

/**
 * signature Util
 *
 * @author cilong
 * @version 2020-04-16
 */
public class SignUtil {
    private static Log loger = LogFactory.getLog(SignUtil.class);

    /**
     * 生成签名信息
     *
     * @param secretKey 产品私钥
     * @param params    接口请求参数名和参数值map，不包括signature参数名
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String genSignature(String secretKey, SortedMap<String, String> params) {
        // 1. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> s : params.entrySet()) {
            String k = s.getKey();
            String v = s.getValue();
            if (!StringUtils.isEmpty(v)) {// 过滤空值
                sb.append(k).append("=").append(v).append("&");
            }
        }
        // 2. 将secretKey拼接到最后
        sb.deleteCharAt(sb.length() - 1);
        sb.append(secretKey);
        return signSha1(sb.toString());
    }

    public static String signSha1(String content) {
        try {
            return DigestUtils.sha1Hex(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            loger.error("sign Error:" + e.getMessage());
            return "";
        }
    }
}
