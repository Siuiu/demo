package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.ValidEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liuyang
 * @Date 2023/7/3 19:50
 **/
@RestController
@RequestMapping("/valid")
public class ValidController {
    @GetMapping("/test1")
    public SmResult test1(@Validated  ValidEntity validEntity){
        return SmResult.ok();
    }
    @GetMapping("/test2")
    public SmResult test2(@RequestBody ValidEntity validEntity){
        System.out.println(JSONUtil.toJsonStr(validEntity));
        return SmResult.ok();
    }
}
