package com.ly.demo.exception;

import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.SmResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author liuyang
 * @Date 2022-11-08 11:12
 **/
@Slf4j
@RestControllerAdvice(basePackages = "com.ly.demo.controller")
public class ExceptionControllerAdvice {
    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     *
     * @return SmResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SmResult handleValidException(MethodArgumentNotValidException e) {
        log.error("数据效验出现问题{},异常类型{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        String msg =e.getBindingResult().getFieldErrors().stream().map(error->error.getField()+":"+error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return SmResult.fail(400, msg);
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public SmResult handleError(BindException e) {
        FieldError fieldError = e.getFieldError();
        String field = fieldError.getField();
        if (fieldError.getDefaultMessage().startsWith("Failed to convert property")) {
            return SmResult.fail("参数格式错误");
        }
        return SmResult.fail(field + fieldError.getDefaultMessage());
    }

    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     *
     * @return SmResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public SmResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("请求消息体不可读:{}", e.getMessage());

        return SmResult.fail(400, "提交的数据格式化错误");
    }

}
