package com.ly.demo.controller;

import com.ly.demo.entity.AccountEntity;
import com.ly.demo.entity.SmResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author liuyang
 * @Date 2022-11-08 10:40
 **/
@Slf4j
@RestController
public class LoginController {
    @PostMapping("/login")
    public SmResult login(@RequestBody @Valid AccountEntity accountEntity){
        log.info("===========>{}",accountEntity);
        return SmResult.ok();
    }
}
