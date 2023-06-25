package com.ly.demo.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.StructureEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liuyang
 * @Date 2023/5/10 17:54
 **/
public class CommonUtil {
    public static void getComplementSet(String s1,String s2){
        List<String> split1 = StrUtil.split(s1, ",");
        List<String> split2 = StrUtil.split(s2, ",");
        List<String> collect = split2.stream().filter(s -> !split1.contains(s)).collect(Collectors.toList());
        split1.stream().filter(s -> !split2.contains(s)).map(a->collect.add(a)).collect(Collectors.toList());
        String join = StrUtil.join(",", collect);
        System.out.println(JSONUtil.toJsonStr(join));
    }
    public static String getClassStruct(String className){
        String json=null;
        try {
            // 加载类
            Class<?> clazz = Class.forName(className);

            // 创建一个对象实例
            Object object = clazz.newInstance();

            // 获取类的字段
            Field[] fields = clazz.getDeclaredFields();

            // 创建一个包含字段名和字段值的简单结构体
            List<StructureEntity>structuries=new ArrayList<>();

            // 遍历字段并获取值
            for (Field field : fields) {
                StructureEntity structure = new StructureEntity();
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                structure.addField(fieldName,fieldType);
                structuries.add(structure);
            }

            // 将结构体转换为 JSON
            json = JSONUtil.toJsonStr(structuries);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void main(String[] args) {
        String classStruct = CommonUtil.getClassStruct("com.ly.demo.entity.AdminEntity");
        System.out.println(classStruct);
    }
}
