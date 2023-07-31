package com.ly.demo.scheduled;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ly.demo.entity.ApiReportLogEntity;
import com.ly.demo.entity.ApiReportLogStatisticsEntity;
import com.ly.demo.mapper.ApiReportLogMapper;
import com.ly.demo.mapper.ApiReportLogStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @Author liuyang
 * @Date 2023/7/28 14:20
 **/
@Component
public class ApiReportLogTask {
    @Autowired
    private ApiReportLogMapper apiReportLogMapper;
    @Autowired
    private ApiReportLogStatisticsMapper apiReportLogStatisticsMapper;

    @PostConstruct
    public void report() {
        LambdaQueryWrapper<ApiReportLogEntity> queryWrapper = Wrappers.lambdaQuery(ApiReportLogEntity.class).apply("DATE(access_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)");
        Double yesterDay = Convert.toDouble(apiReportLogMapper.selectCount(queryWrapper));
        queryWrapper.in(ApiReportLogEntity::getBizCode, Arrays.asList("0", "200"));
        Double yesterDaySuccess = Convert.toDouble(apiReportLogMapper.selectCount(queryWrapper));
        Double successRatio = yesterDaySuccess / yesterDay;
        ApiReportLogStatisticsEntity entity = ApiReportLogStatisticsEntity.builder().accessTime(DateUtil.offsetDay(DateUtil.parseDate(DateUtil.today()), -1))
                .successRate(successRatio).build();
        apiReportLogStatisticsMapper.insert(entity);
    }
}
