package com.ly.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author liuyang
 * @Date 2023/6/21 13:55
 **/
@Data
@Accessors(chain = true)
public class InterfaceEntity {
    private String url;
    private String type;
    private List<ParameterEntity> parameters;
}
