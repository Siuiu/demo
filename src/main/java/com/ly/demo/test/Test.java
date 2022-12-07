package com.ly.demo.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Array;

/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
public class Test {
    public static void main(String[] args) {
        new Array()
    }
    public static void msFormat(){
        DateTime date = DateUtil.date(Convert.toDate(11111));
        System.out.println(date);
    }
}

