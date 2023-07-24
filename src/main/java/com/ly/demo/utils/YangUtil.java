package com.ly.demo.utils;

import javax.servlet.http.HttpServletRequest;

public class YangUtil {
    /**
     * 获取外部地址
     * @param request
     * @return
     */
    public static String getExternalIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        try {
            ip = ip.split(",")[0];
        } catch (Exception e) {
            ip = request.getRemoteHost();
        }
        return ip;
    }

}
