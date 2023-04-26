package com.ly.demo.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.stream.Collectors;

public class LiuUtil {
    public static void getApiUrl(){
        String json= FileUtil.readUtf8String("/Users/liuyang/IdeaProjects/demo/src/main/resources/api_url.text");
        //部分原始数据url前面带#,需要去除, 一个菜单下url中有多个接口的地址,","分隔
        List<String> split = StrUtil.split(json,",");
        List<String> collect = split.stream().distinct().collect(Collectors.toList());
        String join = StrUtil.join(",", collect);
        System.out.println(join);
    }

}
