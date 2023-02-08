package com.ly.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.ly.demo.converter.UserConverter;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.entity.UserVo;
import com.ly.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void smsTest() {
        String tenantCode = "liuyang";
        String date = DateUtil.today().replace("-", "");
        String key = date + "_" + tenantCode;
        redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
    }

    @Test
    public void converTest() {
        UserVo liu = UserConverter.INSTANCE.cover(new UserEntity().setId(9).setName("liu").setOld(18));
        System.out.println(liu);
    }

    @Test
    public void test11() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("test", new UserVo().setUsername("刘"));
        System.out.println(JSONUtil.toJsonStr(SmResult.ok(map)));
    }

    @Test
    public void getList() {
        System.out.println(userService.getList());
    }

    @Test
    public void addUser() {
        boolean aaaase = userService.save(new UserEntity(null, "wangwu", 38, null, null));
        System.out.println(aaaase);
    }

    @Test
    public void getRedis() {
        String k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);
    }

    @Test
    public void contextLoads() {
        SmResult list = userService.getList();
        String s = JSONUtil.toJsonStr(list);
        System.out.println(s);
        SmResult smResult = JSONUtil.toBean(s, SmResult.class);
        System.out.println(smResult.getData());

    }

    @Test
    public void hutool() {
        UserEntity userEntity = new UserEntity(1, null, 1, null, null);
        String s = JSONUtil.toJsonStr(userEntity, new JSONConfig().setIgnoreNullValue(false));
        System.out.println(s);
    }

    @Test
    public void easyExcelWrite() {
        List<UserEntity> list = userService.list();
        String fileName = "src/main/resources/simpleWrite.xlsx";
        EasyExcel.write(fileName, UserEntity.class)
                .sheet("模板")
                .doWrite(list);
    }

    @Test
    public void transactionTest() {
        userService.transactionTest1();
        ;
    }
}
