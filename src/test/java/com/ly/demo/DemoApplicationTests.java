package com.ly.demo;

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

import java.util.List;

@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void converTest(){
        UserVo liu = UserConverter.INSTANCE.cover(new UserEntity().setId(9).setName("liu").setOld(18));
        System.out.println(liu);
    }
    @Test
    public void getList(){
        System.out.println(userService.getList());
    }
    @Test
    public void addUser(){
        boolean aaaase = userService.save(new UserEntity(null, "aaaase", 333, null, null));
    }
    @Test
    public void getRedis(){
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
    public void hutool(){
        UserEntity userEntity = new UserEntity(1, null, 1,null,null);
        String s = JSONUtil.toJsonStr(userEntity,new JSONConfig().setIgnoreNullValue(false));
        System.out.println(s);
    }
    @Test
    public void easyExcelWrite(){
        List<UserEntity> list = userService.list();
        String fileName = "src/main/resources/simpleWrite.xlsx";
        EasyExcel.write(fileName, UserEntity.class)
                .sheet("模板")
                .doWrite(list);
    }

}
