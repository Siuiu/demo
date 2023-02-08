/**
  * Copyright 2023 bejson.com 
  */
package com.ly.demo.shinemo;
import java.util.List;

/**
 * Auto-generated: 2023-02-08 11:28:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ExSignIn {

    private String reportReason;
    private List<String> urls;
    private boolean isReport;
    public void setReportReason(String reportReason) {
         this.reportReason = reportReason;
     }
     public String getReportReason() {
         return reportReason;
     }

    public void setUrls(List<String> urls) {
         this.urls = urls;
     }
     public List<String> getUrls() {
         return urls;
     }

    public void setIsReport(boolean isReport) {
         this.isReport = isReport;
     }
     public boolean getIsReport() {
         return isReport;
     }

}