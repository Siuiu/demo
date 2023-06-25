package com.ly.demo.entity;

import lombok.Data;

/**
 * @Author liuyang
 * @Date 2023/6/25 11:30
 **/
@Data
public class StructureEntity {
    private String fieldName;
    private Object fieldValue;
    private String fieldType;
    public void addField(String name, String type) {
        fieldName = name;
        fieldType = type;
    }
}

