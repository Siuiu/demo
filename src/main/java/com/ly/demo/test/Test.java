package com.ly.demo.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ly.demo.service.UserService;
import com.ly.demo.shinemo.CheckWorkUtil;
import com.ly.demo.utils.StaticUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
public class Test {
    @Resource
    private UserService userService;

    @Resource
    private StaticUtil staticUtil;

    private static UserService u;

    public static void main(String[] args) {
        CheckWorkUtil.getWorkDay(null);
    }


    public static void msFormat() {
        DateTime date = DateUtil.date(Convert.toDate(1671619891648l));
        System.out.println(date);
    }

    @PostConstruct
    public void init() {
        u = this.userService;
    }
}

