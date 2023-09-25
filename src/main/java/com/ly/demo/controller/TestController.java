package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuyang
 * @Date 2023/6/21 14:11
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/hello")
    public SmResult hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        StringBuilder modifiedCookie = new StringBuilder();
        for (Cookie cookie : cookies) {
            if ("b_envName".equals(cookie.getName())) {
            }
        }

        String finalCookieString = modifiedCookie.toString();
        System.out.println(finalCookieString);
        return SmResult.ok(JSONUtil.toJsonStr(request.getHeaderNames()));
    }

    @PostMapping("/test01")
    public SmResult test01(@RequestBody List<Long> uids) {
        return SmResult.ok(uids);
    }


}
