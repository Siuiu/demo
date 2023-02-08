/**
  * Copyright 2023 bejson.com 
  */
package com.ly.demo.shinemo;
import java.util.Date;

/**
 * Auto-generated: 2023-02-08 11:28:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DutyTimes {

    private int dutyType;
    private int status;
    private int dutyIndex;
    private Date dutyTime;
    private String address;
    private String mustSignReason;
    private ExSignIn exSignIn;
    private int dutyTimes;
    private boolean needCover;
    private Date realDutyTime;
    private boolean isUpdateSign;
    private int id;
    public void setDutyType(int dutyType) {
         this.dutyType = dutyType;
     }
     public int getDutyType() {
         return dutyType;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setDutyIndex(int dutyIndex) {
         this.dutyIndex = dutyIndex;
     }
     public int getDutyIndex() {
         return dutyIndex;
     }

    public void setDutyTime(Date dutyTime) {
         this.dutyTime = dutyTime;
     }
     public Date getDutyTime() {
         return dutyTime;
     }

    public void setAddress(String address) {
         this.address = address;
     }
     public String getAddress() {
         return address;
     }

    public void setMustSignReason(String mustSignReason) {
         this.mustSignReason = mustSignReason;
     }
     public String getMustSignReason() {
         return mustSignReason;
     }

    public void setExSignIn(ExSignIn exSignIn) {
         this.exSignIn = exSignIn;
     }
     public ExSignIn getExSignIn() {
         return exSignIn;
     }

    public void setDutyTimes(int dutyTimes) {
         this.dutyTimes = dutyTimes;
     }
     public int getDutyTimes() {
         return dutyTimes;
     }

    public void setNeedCover(boolean needCover) {
         this.needCover = needCover;
     }
     public boolean getNeedCover() {
         return needCover;
     }

    public void setRealDutyTime(Date realDutyTime) {
         this.realDutyTime = realDutyTime;
     }
     public Date getRealDutyTime() {
         return realDutyTime;
     }

    public void setIsUpdateSign(boolean isUpdateSign) {
         this.isUpdateSign = isUpdateSign;
     }
     public boolean getIsUpdateSign() {
         return isUpdateSign;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

}