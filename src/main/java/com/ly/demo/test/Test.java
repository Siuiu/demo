package com.ly.demo.test;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
@Component
@Slf4j
public class Test {
    private static UserService u;

    public static void main(String[] args) {
        String j1 = "{\"id\":\"1\",\"ip\":\"1\"}\n";
        String j2 = "{\"ip\":\"1\"}\n";
        Map m1 = JSONUtil.parse(j1).toBean(Map.class);
        Map m2 = JSONUtil.parse(j2).toBean(Map.class);
        System.out.println(compareMapContainLeft(m1, m2).toString());
        //CheckWorkUtil.getWorkDay(null);
    }

    public static SmResult compareMapContainLeft(Map<String, String> m1, Map<String, String> m2) {
        if (MapUtil.isEmpty(m1)) {
            return SmResult.ok();
        }
        for (String k1 : m1.keySet()) {
            String v1 = m1.get(k1);
            String v2 = m2.get(k1);
            if ((!m2.containsKey(k1)) ||
                    (!StrUtil.hasEmpty(v1, v2) && !v1.equals(v2))) {
                return SmResult.fail(k1 + "解析失败");
            }
        }
        return SmResult.ok();
    }

    public void sout(String s1, String s2) {
        System.out.println("6666");
    }
}