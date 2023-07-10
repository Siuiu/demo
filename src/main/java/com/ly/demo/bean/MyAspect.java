package com.ly.demo.bean;

import com.ly.demo.entity.SmResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author liuyang
 * @Date 2023/6/26 15:40
 **/
@Aspect
@Component
public class MyAspect {
    @Around("execution(* com.ly.demo.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在目标方法执行前的自定义操作
        System.out.println("Before executing the method");

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 在目标方法执行后的自定义操作
        System.out.println("After executing the method");

        // 返回结果
        return result;
    }
}
