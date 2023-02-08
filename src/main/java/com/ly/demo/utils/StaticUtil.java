package com.ly.demo.utils;

import com.ly.demo.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


public class StaticUtil {
    @Resource
    private UserService service;
    public static UserService userService;

    @PostConstruct
    public void init() {
        userService = this.service;
    }
}
