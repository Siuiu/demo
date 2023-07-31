package com.ly.demo.controller;

import com.ly.demo.entity.SmResult;
import com.ly.demo.mapper.UserMapper;
import com.ly.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public SmResult getUserById(@RequestParam Integer id){
        return service.getUserById(id);
    }
    @GetMapping("/yesterDay")
    public SmResult yesterDay(){
        return service.yesterDay();
    }
}
