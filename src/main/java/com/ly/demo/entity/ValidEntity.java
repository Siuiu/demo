package com.ly.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @Author liuyang
 * @Date 2023/7/3 19:52
 **/
@Data
public class ValidEntity {
    @NotBlank
    private String id;
    private HashMap<Object,Object> requestBody;
}
