package com.ly.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.mapper.UserMapper;
import com.ly.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author liuyang
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-10-31 15:49:04
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{
    @Resource
    UserMapper mapper;
    @Override
    public SmResult getList() {
        String className = new Throwable().getStackTrace()[1].getClassName();
        log.info("============>{}",className);
        IPage<UserEntity> page = new Page<>();
        //设置每页条数
        page.setSize(2);
        //设置查询第几页
        page.setCurrent(2);
        this.page(page, null);
        return SmResult.ok().data(page);
    }
}




