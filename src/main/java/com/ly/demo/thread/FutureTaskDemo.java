package com.ly.demo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author liuyang
 * @Date 2023/7/26 15:42
 **/
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> f1 = new FutureTask<>(() -> {
            Thread.sleep(5000L);
            return "6";
        });
        Thread t1 = new Thread(f1);
        t1.start();
        CompletableFuture<String> f2=new CompletableFuture<>();
        //会阻塞接下来的代码块
        System.out.println(f1.get());
        System.out.println("main out");
    }
}
