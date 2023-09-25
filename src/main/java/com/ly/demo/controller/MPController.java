package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author liuyang
 * @Date 2023/7/11 10:23
 **/
@RestController
@RequestMapping("/mp")
public class MPController {
    @Resource
    private UserService service;

    @GetMapping("/getUserById")
    public SmResult getUserById(@RequestParam Integer id) {
        return service.getUserById(id);
    }

    @GetMapping("/yesterDay")
    public SmResult yesterDay() {
        return service.yesterDay();
    }

    @GetMapping("/oderby")
    public SmResult oderby() {
        return service.oderby();
    }

    @PostMapping("/insert")
    public SmResult insert(@RequestBody String name) {
        UserEntity user = new UserEntity();
        user.setName(name);
        service.save(user);
        return SmResult.ok(JSONUtil.toJsonStr(user));
    }

    @PostMapping("/updateUser")
    public SmResult updateUser() {
        service.lambdaUpdate().eq(UserEntity::getId, 1).set(UserEntity::getName, "爱情").update();
        return SmResult.ok();
    }
}
