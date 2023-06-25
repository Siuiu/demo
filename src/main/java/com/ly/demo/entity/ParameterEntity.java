package com.ly.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author liuyang
 * @Date 2023/6/25 10:51
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ParameterEntity {
    private String name;
    private Object type;

    public ParameterEntity(String name, Object type) {
        this.name = name;
        this.type = type;
    }
}
