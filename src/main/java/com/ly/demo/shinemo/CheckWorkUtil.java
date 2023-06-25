package com.ly.demo.shinemo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        ArrayList<String> strings = new ArrayList<>();
        for (int j = 0; j < workTime.getDayInfos().size(); j++) {
            DayInfos dayInfo = workTime.getDayInfos().get(j);
            List<DutyTimes> dutyTimeslist = dayInfo.getDutyTimes();
            if (CollUtil.isEmpty(dutyTimeslist)) {
                continue;
            }
            Date dutyTime = dayInfo.getDutyTimes().get(0).getDutyTime();
            Date 下班时间 = null;
            String format下班时间 = null;
            if(dayInfo.getDutyTimes().size()>1){
                下班时间 = dayInfo.getDutyTimes().get(1).getDutyTime();
                format下班时间 = DateUtil.format(下班时间, "HH:mm");
            }
            String formatTime = DateUtil.format(dutyTime, "HH:mm");
            String formatDay = DateUtil.format(dutyTime, "yyyy-MM-dd");
            //每日工作时间
            BigDecimal dayWorkingTime = dayInfo.getDayWorkingTime();
            //当月第几周
            int weekOfMonth = DateUtil.weekOfMonth(dutyTime);
            //星期几
            Week week = DateUtil.dayOfWeekEnum(dutyTime);
            System.out.println(formatDay + "   "+formatTime+"--"+format下班时间+"打卡,工作时长:" + CheckWorkUtil.formatBetween(dayWorkingTime));
            if (week == FRIDAY) {
                strings.add("\t\t\t\t\t第" + weekOfMonth + "周工作时长:" + weekWorkTime.add(dayWorkingTime)+"h");
                if(j == workTime.getDayInfos().size() - 1){
                    System.out.println("\t\t\t\t\t今日已工作"+DateUtil.formatBetween(dutyTime, DateUtil.date(), BetweenFormatter.Level.MINUTE));
                    BigDecimal avgDayTime = new BigDecimal(44).subtract(weekWorkTime).subtract(new BigDecimal(7))
                            .subtract(new BigDecimal(7));
                    strings.forEach(s->System.out.println(s));
                    if(avgDayTime.intValue()<0){
                        System.out.println("\t\t\t\t\t第" + weekOfMonth + "周工作时长:" + weekWorkTime+"h,该周工时已满,准点下班!!!");
                        continue;
                    }
                    System.out.println("\t\t\t\t\t第" + weekOfMonth + "周工作时长:" + weekWorkTime+"h,每日还需加班"+CheckWorkUtil.formatBetween(avgDayTime));
                }
                weekWorkTime = new BigDecimal(0);
                continue;
            }
            //计算工时
            weekWorkTime = weekWorkTime.add(dayWorkingTime);
            if (j == workTime.getDayInfos().size() - 1) {
                System.out.println("\t\t\t\t\t今日已工作"+DateUtil.formatBetween(dutyTime, DateUtil.date(), BetweenFormatter.Level.MINUTE));
                BigDecimal avgDayTime = new BigDecimal(44).subtract(weekWorkTime).subtract(new BigDecimal(7))
                        .divide(new BigDecimal(6 - week.getValue()),BigDecimal.ROUND_CEILING).subtract(new BigDecimal(7));
                strings.forEach(s->System.out.println(s));
                if(avgDayTime.intValue()<0){
                    System.out.println("\t\t\t\t\t第" + weekOfMonth + "周工作时长:" + weekWorkTime+"h,该周工时已满,准点下班!!!");
                    continue;
                }
                System.out.println("\t\t\t\t\t第" + weekOfMonth + "周工作时长:" + weekWorkTime+"h,每日还需加班"+CheckWorkUtil.formatBetween(avgDayTime));
            }
        }
    }
    public static String formatBetween(BigDecimal b){
        return DateUtil.formatBetween((long) (b.doubleValue()*60*60*1000), BetweenFormatter.Level.MINUTE);
    }
}
