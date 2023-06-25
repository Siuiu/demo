package com.ly.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.demo.entity.ApiDebugEntity;
import com.ly.demo.mapper.ApiDebugMapper;
import com.ly.demo.service.ApiDebugService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyang
 * @description 针对表【t_api_debug】的数据库操作Service实现
 * @createDate 2023-06-25 16:18:36
 */
@Service
public class ApiDebugServiceImpl extends ServiceImpl<ApiDebugMapper, ApiDebugEntity>
        implements ApiDebugService {

    public void addInterface(List<ApiDebugEntity> apiDebugEntityList) {
        this.remove(null);
        this.saveBatch(apiDebugEntityList);
    }
}




