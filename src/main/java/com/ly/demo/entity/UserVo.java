package com.ly.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author liuyang
 * @Date 2022-11-23 15:23
 **/
@Data
@Accessors(chain = true)
public class UserVo {
    @NotNull
    private Integer id;
    private String username;
    private Integer old;
}
