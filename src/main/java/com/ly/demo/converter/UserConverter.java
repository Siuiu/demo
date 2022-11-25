package com.ly.demo.converter;

import com.ly.demo.entity.UserEntity;
import com.ly.demo.entity.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author liuyang
 * @Date 2022-11-23 15:24
 **/
@Mapper
public interface UserConverter {
    UserConverter INSTANCE=Mappers.getMapper(UserConverter.class);
    @Mapping(target = "username",source = "name")
    UserVo cover(UserEntity entity);
}
