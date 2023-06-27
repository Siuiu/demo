package com.ly.demo.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.InterfaceEntity;
import com.ly.demo.entity.ParameterEntity;
import com.ly.demo.entity.StructureEntity;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.internal.org.objectweb.asm.tree.ParameterNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author liuyang
 * @Date 2023/5/10 17:54
 **/
@Slf4j
public class CommonUtil {
    public static void getComplementSet(String s1, String s2) {
        List<String> split1 = StrUtil.split(s1, ",");
        List<String> split2 = StrUtil.split(s2, ",");
        List<String> collect = split2.stream().filter(s -> !split1.contains(s)).collect(Collectors.toList());
        split1.stream().filter(s -> !split2.contains(s)).map(a -> collect.add(a)).collect(Collectors.toList());
        String join = StrUtil.join(",", collect);
        System.out.println(JSONUtil.toJsonStr(join));
    }

    public static Object getClassStruct(String className) {
        List<StructureEntity> structuries = new ArrayList<>();
        try {
            // 加载类
            Class<?> clazz = Class.forName(className);
            // 获取类的字段
            Field[] fields = clazz.getDeclaredFields();

            // 创建一个包含字段名和字段值的简单结构体

            // 遍历字段并获取值
            for (Field field : fields) {
                StructureEntity structure = new StructureEntity();
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                String typeName = fieldType.getName();
                if (isPrimitiveOrWrapper(fieldType)) {
                    List<String> split = StrUtil.split(typeName, ".");
                    structure.addField(fieldName, split.get(split.size() - 1));
                } else {
                    structure.addField(fieldName, typeName);
                }
                structuries.add(structure);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return structuries;
    }

    public static String getParamList(String className, String methodName) {
        log.info("className:{},methodName:{}", className, methodName);
        List<ParameterEntity> paramList = new ArrayList<>();

        ClassReader reader;
        try {
            reader = new ClassReader(className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        List<String> excludeClass = Arrays.asList("javax.servlet.http.HttpSession", "javax.servlet.http.HttpServletResponse");
        for (MethodNode method : classNode.methods) {
            if (methodName.equals(method.name)) {
                //参数名列表
                List<ParameterNode> parameters = method.parameters;
                //参数类型列表
                Type[] argumentTypes = Type.getArgumentTypes(method.desc);
                //如果没有参数则退出
                if (CollUtil.isEmpty(parameters)) {
                    return null;
                }
                for (int i = 0; i < parameters.size(); i++) {
                    ParameterNode parameter = parameters.get(i);
                    Type argumentType = argumentTypes[i];
                    String paramName = parameter.name;
                    String paramType = argumentType.getClassName();
                    //排除掉一些基础类
                    if (excludeClass.contains(paramType)) {
                        continue;
                    }
                    Class<?> aClass = null;
                    try {
                        aClass = Class.forName(paramType);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ParameterEntity parameterEntity = new ParameterEntity().setName(paramName);
                    //如果是8大基础类型或字符串则使用其名字即可
                    if (isPrimitiveOrWrapper(aClass)) {
                        List<String> split = StrUtil.split(paramType, ".");
                        parameterEntity.setType(split.get(split.size() - 1));
                    } else {
                        //否则则去拿这个类的结构体
                        Object classStruct = CommonUtil.getClassStruct(paramType);
                        parameterEntity.setType(classStruct);
                    }
                    paramList.add(parameterEntity);
                }
                break;
            }
        }
        return JSONUtil.toJsonStr(paramList);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        Set<Class<?>> primitiveOrWrapperTypes = new HashSet<>(Arrays.asList(
                boolean.class, Boolean.class,
                char.class, Character.class,
                byte.class, Byte.class,
                short.class, Short.class,
                int.class, Integer.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class,
                String.class
        ));
        return primitiveOrWrapperTypes.contains(clazz);
    }

    public static void main(String[] args) {
        Object classStruct = CommonUtil.getClassStruct("com.ly.demo.entity.AdminEntity");
        InterfaceEntity interfaceEntity = new InterfaceEntity();
        CommonUtil.getParamList("com.ly.demo.controller.HelloController", "head");
        System.out.println(JSONUtil.toJsonStr(interfaceEntity));
    }
}
