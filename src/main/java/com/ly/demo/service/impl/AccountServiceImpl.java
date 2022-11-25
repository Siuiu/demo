package com.ly.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.demo.entity.AccountEntity;
import com.ly.demo.mapper.AccountMapper;
import com.ly.demo.service.AccountService;
import org.springframework.stereotype.Service;

/**
* @author liuyang
* @description 针对表【t_account】的数据库操作Service实现
* @createDate 2022-11-08 10:46:14
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity>
    implements AccountService {

}




