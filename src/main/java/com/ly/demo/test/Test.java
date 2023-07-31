package com.ly.demo.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.javafaker.Faker;
import com.ly.demo.service.UserService;
import com.ly.demo.shinemo.CheckWorkUtil;
import com.ly.demo.thread.ApiReportLogThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;

import static cn.hutool.core.date.DateField.HOUR;


/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
@Component
@Slf4j
public class Test {
    private static UserService u;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = ThreadUtil.newExecutor(10);
        for(int i=0;i<5;i++){
            ApiReportLogThread apiReportLogThread = new ApiReportLogThread();
            executorService.submit(apiReportLogThread);
        }
    }
}