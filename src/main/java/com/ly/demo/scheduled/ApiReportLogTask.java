package com.ly.demo.scheduled;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ly.demo.entity.ApiReportLogEntity;
import com.ly.demo.entity.ApiReportLogStatisticsEntity;
import com.ly.demo.mapper.ApiReportLogMapper;
import com.ly.demo.mapper.ApiReportLogStatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @Author liuyang
 * @Date 2023/7/28 14:20
 **/
@Slf4j
@Transactional
//@Component
public class ApiReportLogTask {
    private static final String REDIS_LOCK_KEY = "report:ApiReportLogTask:task:lock";
    @Autowired
    private ApiReportLogMapper apiReportLogMapper;
    @Autowired
    private ApiReportLogStatisticsMapper apiReportLogStatisticsMapper;

    //@Scheduled(cron = "0 0 3 * * ?")
    @PostConstruct
    public void report() {
        //1.统计并添加
        //1.1 查询昨天的总数
        LambdaQueryWrapper<ApiReportLogEntity> queryWrapper = Wrappers.lambdaQuery(ApiReportLogEntity.class).apply("DATE(access_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)");
        Double yesterDay = Convert.toDouble(apiReportLogMapper.selectCount(queryWrapper));

        //1.2 查询昨天的成功数
        queryWrapper.in(ApiReportLogEntity::getBizCode, Arrays.asList("0", "200"));
        Double yesterDaySuccess = Convert.toDouble(apiReportLogMapper.selectCount(queryWrapper));

        //1.3 计算昨天的成功率
        Double successRatio;
        if(yesterDay==0||yesterDaySuccess==0){
            successRatio=0.00;
        }else {
            successRatio= yesterDaySuccess / yesterDay;
        }

        //2.1.如果存在昨天统计的数据 先删除
        apiReportLogStatisticsMapper.delete(Wrappers.lambdaQuery(ApiReportLogStatisticsEntity.class).apply("DATE(access_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)"));
        //2.2. 添加
        ApiReportLogStatisticsEntity entity = ApiReportLogStatisticsEntity.builder().accessTime(DateUtil.offsetDay(DateUtil.parseDate(DateUtil.today()), -1))
                .successRate(successRatio).build();
        apiReportLogStatisticsMapper.insert(entity);
    }
}
