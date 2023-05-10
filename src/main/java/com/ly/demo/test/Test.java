package com.ly.demo.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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
        String s1="1,7,13,19,23,27,36,2,5,6,8,14,15,10096,20,21,22,24,25,26,28,29,37,40,42,962,10098";
        String s2="1,2,5,6,19,20,21,22,23,24,25,26,27,28,29,10069,36,37,40,42,10098,10099,10100,10101,7,8,962,963,10065,13,14,15,10096,10110,10111";
        List<String> split1 = StrUtil.split(s1, ",");
        List<String> split2 = StrUtil.split(s2, ",");
        List<String> collect = split2.stream().filter(s -> !split1.contains(s)).collect(Collectors.toList());
        split1.stream().filter(s -> !split2.contains(s)).map(a->collect.add(a)).collect(Collectors.toList());
        String join = StrUtil.join(",", collect);
        System.out.println(JSONUtil.toJsonStr(join));


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

