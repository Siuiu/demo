package com.ly.demo.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author liuyang
 * @Date 2022-10-27 20:29
 **/
@Component
//@EnableScheduling
public class SeckillScheduled {
    @Scheduled(cron = "0/2 * * * * ? ")
    public void uploadSeckillSkuLatest3Days() {
        System.out.println(6666);
    }
}
