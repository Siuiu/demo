package com.ly.demo.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.ly.demo.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
@Component
@Slf4j
public class Test {
    @Resource
    private UserService userService;

    @Value("${baas.role}")
    private String menuIds;
    private static UserService u;

    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException {
        Class<?> aClass = Class.forName("com.ly.demo.entity.UserEntity");
        System.out.println(JSONUtil.toJsonStr(aClass.getName()));
//        Integer price=15750-3828-2000+3174+1300+1280+640+1190+200+1000+600+1200+2000+5600+1700+800+500+730-1300;
//        System.out.println(price);
//        Date date = DateUtil.date();
//        String format = DateUtil.format(date, "yyyy-MM-dd");
//        System.out.println(format);
//        DateTime lastTime = DateUtil.offsetDay(date, 7);
//        boolean isExceedSevenDays = DateUtil.isIn(lastTime, DateUtil.date(), DateUtil.lastWeek());
//        System.out.println(date+"==="+lastTime+"==="+isExceedSevenDays);
        //CheckWorkUtil.getWorkDay(null);
    }

    public static void test(List<Long> uids) {
        String fileName = "src/main/resources/simpleWrite.xlsx";
        ExcelReaderBuilder read = EasyExcel.read(fileName);

    }


    public static void msFormat() {
        DateTime date = DateUtil.date(Convert.toDate(1671619891648l));
        System.out.println(date);
    }

    @PostConstruct
    public void init() {
        u = this.userService;
    }
}

@Data
class Liud {
    private Map<String, Object> extension;

}