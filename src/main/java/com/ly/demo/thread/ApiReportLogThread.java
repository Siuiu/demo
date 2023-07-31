package com.ly.demo.thread;

import com.ly.demo.utils.YangUtil;

import java.util.concurrent.Callable;

/**
 * @Author liuyang
 * @Date 2023/7/31 20:33
 **/
public class ApiReportLogThread implements Runnable {
    @Override
    public void run() {
        YangUtil.randomSqlData();
    }
}
