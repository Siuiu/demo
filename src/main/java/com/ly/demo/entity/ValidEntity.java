package com.ly.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author liuyang
 * @Date 2023/7/3 19:52
 **/
@Data
public class ValidEntity {
    @NotNull
    private Long id;
}
