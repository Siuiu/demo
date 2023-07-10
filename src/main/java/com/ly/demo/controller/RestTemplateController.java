package com.ly.demo.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Author liuyang
 * @Date 2023/7/5 17:18
 **/
@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    private final String hello = "http://localhost:8088/hello";
    private final String helloPeople = "http://localhost:8088/helloPeople";

    @GetMapping("/sendHello")
    public void sendHello() {
        RestTemplate restTemplate = new RestTemplate();
        // 发送 GET 请求
        ResponseEntity<String> response = restTemplate.getForEntity(hello, String.class);
        // 获取响应结果
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("Response: " + responseBody);
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCode());
        }
    }

    @GetMapping("/okhttpSend")
    public void okhttpSend() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(hello)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("Response: " + response.body());
    }

    @GetMapping("/sendHelloName")
    public void sendHelloName() {
        RestTemplate restTemplate = new RestTemplate();
        HttpMethod method = HttpMethod.GET;
        LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
        param.add("s1", "liu");
        HttpEntity<LinkedMultiValueMap<Object, Object>> linkedMultiValueMapHttpEntity = new HttpEntity<>(param);
        // 发送 GET 请求
        ResponseEntity<String> response = restTemplate.exchange(helloPeople,method,linkedMultiValueMapHttpEntity, String.class);
        // 获取响应结果
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("Response: " + responseBody);
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCode());
        }
    }

    @GetMapping("/okhttpSendName")
    public void okhttpSendName() throws Exception {
    }

}
