package com.ly.demo.controller;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuyang
 * @Date 2023/7/7 16:41
 **/
@RestController
@RequestMapping("/url")
public class UrlController {
    @GetMapping("/mock-api/**")
    public void mockGet(HttpServletRequest request) {
        List<String> split = StrUtil.split(request.getRequestURL(), "/mock-api");
        System.out.println(split.get(1) + " - " + request.getMethod());
    }

    @PostMapping("/mock-api/**")
    public void mockPost(HttpServletRequest request) {
        List<String> split = StrUtil.split(request.getRequestURL(), "/mock-api");
        System.out.println(split.get(1) + " - " + request.getMethod());
    }

    @PostMapping("/request")
    public void request(HttpServletRequest request, @RequestBody Map<String, String> requestBody) {
        Map<String, String> requestHeaders = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeaders.put(headerName, headerValue);
        }
        Map<String, String> requestQuery = new HashMap<>();
        String query = request.getQueryString();
        List<String> split = StrUtil.split(query, "&");
        for (String s : split) {
            List<String> queryKV = StrUtil.split(s, "=");
            requestQuery.put(queryKV.get(0), queryKV.get(1));
        }
        System.out.println(JSONUtil.toJsonStr(requestQuery));
        System.out.println(JSONUtil.toJsonStr(requestBody));
        System.out.println(requestHeaders);
    }
    @GetMapping("/getRemoteAddr")
    public void getRemoteAddr(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies){
            if("admin_login".equals(c.getName())){
                String decode = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                Map bean = JSONUtil.toBean(decode, Map.class);
                Object o = bean.get("bisSysId");
            }
        }

        System.out.println(request.getRemoteAddr());
    }
}
