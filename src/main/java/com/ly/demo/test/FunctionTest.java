package com.ly.demo.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @Author liuyang
 * @Date 2023/7/18 14:24
 **/
public class FunctionTest {
    public static <T> T test(Function<String, T> toStrFunc) {
        return toStrFunc.apply("liu");
    }

    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println(DateUtil.now());
            Thread.sleep(1000);
        }
        //UserEntity test = test((t) -> new UserEntity().setName(t));
    }
}
