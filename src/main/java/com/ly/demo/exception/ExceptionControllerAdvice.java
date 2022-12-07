package com.ly.demo.exception;

import com.ly.demo.entity.SmResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuyang
 * @Date 2022-11-08 11:12
 **/
@Slf4j
@RestControllerAdvice(basePackages = "com.ly.demo.controller")
public class ExceptionControllerAdvice {
    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     * @return SmResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SmResult handleValidException(MethodArgumentNotValidException e) {
        log.error("数据效验出现问题{},异常类型{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> errMap.put(fieldError.getField(),fieldError.getDefaultMessage()));
        return SmResult.fail(400,"提交的数据不合法").data(errMap);
    }
}
