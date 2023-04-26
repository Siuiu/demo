package com.ly.demo.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ly.demo.entity.UserEntity;

import java.util.List;

public class ExcelReaderTest {
    public static void main(String[] args) {
        String filename = "simpleWrite.xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filename),0);
        reader.addHeaderAlias("username","name");
        List<UserEntity> list = reader.readAll(UserEntity.class);
        for (UserEntity user:list){
        }
    }
}
