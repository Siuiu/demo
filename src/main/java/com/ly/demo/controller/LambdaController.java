package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.mapper.UserMapper;
import com.ly.demo.valid.UpdateUserReqVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * @Author liuyang
 * @Date 2023/7/19 19:33
 **/
@RestController
@RequestMapping("/lambda")
public class LambdaController {
    @Resource
    private UserMapper userMapper;

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UpdateUserReqVO reqVO) {

    }

    public static void update(Consumer<String> user) {
        user.accept("6");
    }

    public static void main(String[] args) {
        UserEntity user = new UserEntity();
        user.setId(9);
        update(t->user.setName(t));
        System.out.println(JSONUtil.toJsonStr(user));
    }
}
