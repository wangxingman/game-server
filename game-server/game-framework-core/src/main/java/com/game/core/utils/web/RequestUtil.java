package com.game.core.utils.web;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 7:50 2019/5/6 0006
 */
@Slf4j
public class RequestUtil {

    /**
     * 未知
     */
    public final static String UNKNOWN = "unknown";

    /**
     * 获取客户端ip
     *
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
}
