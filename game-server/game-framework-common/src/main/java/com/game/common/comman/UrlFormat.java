package com.game.common.comman;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:15 2019/7/23 0023
 * @explain :
 */
public class UrlFormat {
    /**
     * 编码
     *
     * @param s
     *            字符串
     * @return 编码之后的字符串
     */
    public static String encode(String s) {
        try {
            if (s == null) {
                return null;
            }
            return URLEncoder.encode(s, Default.ENCODING);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解码
     *
     * @param s
     *            字符串
     * @return 解码之后的字符串
     */
    public static String decode(String s) {
        try {
            if (s == null) {
                return null;
            }
            return URLDecoder.decode(s, Default.ENCODING);
        } catch (Exception e) {
            return null;
        }
    }
}
