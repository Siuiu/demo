/**
  * Copyright 2023 bejson.com 
  */
package com.ly.demo.shinemo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2023-02-08 11:28:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class DayInfos {
    private List<String> approveList;
    private List<DutyTimes> dutyTimes;
    private BigDecimal dayWorkingTime;

}