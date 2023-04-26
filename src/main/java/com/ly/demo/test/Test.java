package com.ly.demo.test;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.service.UserService;
import com.ly.demo.shinemo.CheckWorkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
@Component
@Slf4j
public class Test {
    @Resource
    private UserService userService;

    @Value("${baas.role}")
    private String menuIds;
    private static UserService u;

    public static void main(String[] args) {


        ArrayList<Long> list = ListUtil.toList(1l ,2l);
        list.contains(null);


        //CheckWorkUtil.getWorkDay(null);

    }
    public static void test(List<Long> uids){
        String fileName = "src/main/resources/simpleWrite.xlsx";
        ExcelReaderBuilder read = EasyExcel.read(fileName);

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

