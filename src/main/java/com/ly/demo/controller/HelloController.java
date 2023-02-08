package com.ly.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserVo;
import com.ly.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private UserService service;
    private static UserService userService;

    @GetMapping("/hello")
    public SmResult hello() {
        return userService.getList();
    }

    @GetMapping("/VerificationCode")
    public void imageCode(HttpSession session, HttpServletResponse response) throws IOException {
        GifCaptcha shearCaptcha = CaptchaUtil.createGifCaptcha(300, 100, 4);
        //获取到4位数的验证码
        String code = shearCaptcha.getCode();
        log.info("验证码--->{}", code);
        ServletOutputStream sos = response.getOutputStream();
        shearCaptcha.write(sos);
    }

    @GetMapping("/head")
    public void head(@RequestBody UserVo vo, @RequestParam String like) {
        System.out.println(like);
        log.info("uid--->{}", JSONUtil.toJsonStr(vo));
    }

    @PostConstruct
    public void init() {
        userService = this.service;
    }
}
