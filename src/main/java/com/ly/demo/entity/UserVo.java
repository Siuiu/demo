package com.ly.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author liuyang
 * @Date 2022-11-23 15:23
 **/
@Data
@Accessors(chain = true)
public class UserVo {
    private Integer id;
    private String username;
    private Integer old;
}
