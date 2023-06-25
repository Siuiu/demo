package com.ly.demo.controller;

import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author liuyang
 * @Date 2023/6/21 14:11
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController
{
    @PostMapping("/hello")
    public SmResult hello() {
        return SmResult.ok();
    }

}
