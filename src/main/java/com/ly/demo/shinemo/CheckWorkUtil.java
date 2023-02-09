package com.ly.demo.shinemo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.Week.FRIDAY;

/**
 * @Author liuyang
 * @Date 2023/2/8 15:49
 **/
public class CheckWorkUtil {
    public static void getWorkDay(String filename) {
        String json=null;
        if(StrUtil.isBlank(filename)){
            json = FileUtil.readUtf8String("/Users/liuyang/IdeaProjects/demo/src/main/resources/checkWork.text");
        }else {
            json = FileUtil.readUtf8String("/Users/liuyang/IdeaProjects/demo/src/main/resources/"+filename+".text");
        }
        JsonRootBean workTime = JSONUtil.toBean(json, JsonRootBean.class);
        BigDecimal weekWorkTime = new BigDecimal(0);
        for (int j = 0; j < workTime.getDayInfos().size(); j++) {
            DayInfos dayInfo = workTime.getDayInfos().get(j);
            List<DutyTimes> dutyTimeslist = dayInfo.getDutyTimes();
            if (CollUtil.isEmpty(dutyTimeslist)) {
                continue;
            }
            Date dutyTime = dayInfo.getDutyTimes().get(0).getDutyTime();
            String formatDay = DateUtil.format(dutyTime, "yyyy-MM-dd");
            //每日工作时间
            BigDecimal dayWorkingTime = dayInfo.getDayWorkingTime();
            //当月第几周
            int weekOfMonth = DateUtil.weekOfMonth(dutyTime);
            //星期几
            Week week = DateUtil.dayOfWeekEnum(dutyTime);
            System.out.println(formatDay + "   工作时长:" + dayWorkingTime);
            if (week == FRIDAY) {
                System.out.println("             第" + weekOfMonth + "周工作时长:" + weekWorkTime.add(dayWorkingTime));
                weekWorkTime = new BigDecimal(0);
                continue;
            }
            //计算工时
            weekWorkTime = weekWorkTime.add(dayWorkingTime);
            if (j == workTime.getDayInfos().size() - 1) {
                System.out.println("             今日已工作"+DateUtil.formatBetween(dutyTime, DateUtil.date(), BetweenFormatter.Level.MINUTE));
                BigDecimal avgDayTime = new BigDecimal(44).subtract(weekWorkTime).subtract(new BigDecimal(7))
                        .divide(new BigDecimal(6 - week.getValue()),BigDecimal.ROUND_CEILING).subtract(new BigDecimal(7));
                if(avgDayTime.intValue()<0){
                    System.out.println("             第" + weekOfMonth + "周工作时长:" + weekWorkTime+",该周工时已满,准点下班!!!");
                    continue;
                }
                System.out.println("             第" + weekOfMonth + "周工作时长:" + weekWorkTime+",每日还需加班"+avgDayTime+"小时");
            }
        }
    }
}
