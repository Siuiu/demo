package com.ly.demo.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.demo.entity.SmResult;
import com.ly.demo.entity.UserEntity;
import com.ly.demo.exception.SHException;
import com.ly.demo.mapper.UserMapper;
import com.ly.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author liuyang
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-10-31 15:49:04
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {
    @Resource
    UserMapper mapper;

    @Override
    @Transactional
    public SmResult getList() {
        String className = new Throwable().getStackTrace()[1].getClassName();
        log.info("============>{}", className);
        IPage<UserEntity> page = new Page<>();
        //设置每页条数
        page.setSize(2);
        //设置查询第几页
        page.setCurrent(2);
        this.page(page, null);
        return SmResult.ok().data(page);
    }
    @Transactional(rollbackFor = Exception.class)
    public void transactionTest1(){
        this.transactionTest12();
        throw new SHException("6");
    }
    @Value("#{'${baas.role}'.split(',')}")
    private List<Long> menuIds;
    @Override
    public String getValue() {
        return JSONUtil.toJsonStr(menuIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transactionTest12() {
        UserEntity user = new UserEntity().setId(13).setName("dsad");
        mapper.updateById(user);
    }
}




