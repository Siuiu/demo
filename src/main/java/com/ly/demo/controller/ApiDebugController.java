package com.ly.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ly.demo.entity.ApiDebugEntity;
import com.ly.demo.entity.SmResult;
import com.ly.demo.mapper.ApiDebugMapper;
import com.ly.demo.vo.MockReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Wrapper;

/**
 * @Author liuyang
 * @Date 2023/6/26 15:00
 **/
@Slf4j
@RestController
public class ApiDebugController {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private ApiDebugMapper apiDebugMapper;

    @PostMapping("/mock/{reqVO}")
    public SmResult info(@PathVariable("reqVO") String reqVO) {
        System.out.println(reqVO);
        return SmResult.ok();
    }
    @PostMapping("/mock/info")
    public SmResult mock(@RequestBody MockReqVO reqVO) {
        return SmResult.ok();
    }

    @PostMapping("/setApiDebug")
    public void setApiDebug(@RequestParam Integer id) {
        UpdateWrapper updateWrapper = new UpdateWrapper<ApiDebugEntity>();
        updateWrapper.set("request_type",null);
        updateWrapper.eq("id",id);
        apiDebugMapper.update(null, updateWrapper);
    }
}
