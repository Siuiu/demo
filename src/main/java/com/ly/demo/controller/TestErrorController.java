package com.ly.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author liuyang
 * @Date 2023/6/21 15:08
 *
 *
 * 继承ErrorController并重写/error请求
 **/
@Slf4j
@RestController
public class TestErrorController implements ErrorController {
//    @RequestMapping("/error")
//    public String handleError(HttpServletResponse response) {
//        Integer statusCode = response.getStatus();
//        if (statusCode == 200) {
//            return "成功成功成功成功成功成功成功成功";
//        } else if (statusCode == 400) {
//            return "客户端请求错误";
//        } else if (statusCode == 500) {
//            return "服务端内部错误";
//        }
//        log.info(statusCode.toString());
//        return "未知错误";
//    }
}
