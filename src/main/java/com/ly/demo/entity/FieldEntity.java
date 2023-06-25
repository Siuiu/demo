package com.ly.demo.entity;

import java.io.Serializable;

/**
 * @Author liuyang
 * @Date 2023/6/25 11:02
 **/
public class FieldEntity implements Serializable {
    private String name;
    private String type;

    public FieldEntity(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
