package com.ly.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;

/**
* @author liuyang
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-11-11 16:30:32
*/
public interface UserService extends IService<UserEntity> {

    SmResult getList();

    void transactionTest1();

    String getValue();
}
