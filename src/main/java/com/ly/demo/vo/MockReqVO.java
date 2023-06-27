package com.ly.demo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author liuyang
 * @Date 2023/6/26 15:04
 **/
@Data
@Accessors(chain = true)
public class MockReqVO {
    private String appName;
    private String interfacePath;
    private String requestParam;
}
