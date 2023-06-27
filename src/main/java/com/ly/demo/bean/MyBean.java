package com.ly.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author liuyang
 * @Date 2023/6/26 15:09
 **/
@Component
public class MyBean {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
