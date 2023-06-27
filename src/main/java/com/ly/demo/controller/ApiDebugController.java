package com.ly.demo.controller;

import com.ly.demo.vo.MockReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author liuyang
 * @Date 2023/6/26 15:00
 **/
@Slf4j
@RestController("/apiDebug")
public class ApiDebugController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/mock/info")
    public String mock(@RequestBody MockReqVO reqVO) {
        String response = restTemplate.getForObject(reqVO.getInterfacePath(), String.class);
        return response;
    }

    @PostMapping("/mock/{reqVO}")
    public String info(@PathVariable("reqVO") MockReqVO reqVO) {
        String response = restTemplate.getForObject(reqVO.getInterfacePath(), String.class);
        return response;
    }
}
