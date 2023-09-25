package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.ValidEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author liuyang
 * @Date 2023/7/3 19:50
 **/
@RestController
@RequestMapping("/valid")
public class ValidController {
    @GetMapping("/test1")
    public SmResult test1(@RequestParam Long id) {
        return SmResult.ok();
    }

    @PostMapping("/test2")
    public SmResult test2(@Validated @RequestBody ValidEntity validEntity) {
        System.out.println(JSONUtil.toJsonStr(validEntity));
        return SmResult.ok();
    }

    @PostMapping("/test3")
    public SmResult test3(@RequestPart MultipartFile file,@RequestParam Long id) {
        return SmResult.ok(file.getName()+id);
    }
}
