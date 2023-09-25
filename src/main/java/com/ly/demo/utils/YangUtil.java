package com.ly.demo.utils;

import com.github.javafaker.Faker;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class YangUtil {
    /**
     * 获取外部地址
     *
     * @param request
     * @return
     */
    public static String getExternalIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        try {
            ip = ip.split(",")[0];
        } catch (Exception e) {
            ip = request.getRemoteHost();
        }
        return ip;
    }




    /**
     * 生成随机数据
     */
    public static void randomSqlData() {
        int numRecords = 1000000;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.2.11.34:3308/zqzt_statistics", "zqzt", "CCBcq8xCpBLvaZPwGQJ8YKTy2zN")) {
            Faker faker = new Faker();
            Random random = new Random();
            Instant endInstant = Instant.now();
            Instant startInstant = endInstant.minus(7, ChronoUnit.DAYS);

            String query = "INSERT INTO api_report_log (access_time, biz_code) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < numRecords; i++) {
                long randomMillis = startInstant.toEpochMilli() + (long) (random.nextDouble() * (endInstant.toEpochMilli() - startInstant.toEpochMilli()));
                Instant randomInstant = Instant.ofEpochMilli(randomMillis);
                preparedStatement.setTimestamp(1, new java.sql.Timestamp(randomInstant.toEpochMilli()));
                preparedStatement.setInt(2, random.nextInt(3) * 200); // biz_code can be 0, 200, or 500
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("生成完成！数据已插入数据库。");
    }

    /**
     * 生成随机数据
     */
    public static void randomSqlData二期研发() {
        int numRecords = 1000000;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.17.100:3309/zqzt_statistics", "im", "CCBcq8xCpBLvaZPwGQJ8YKTy2zN")) {
            Faker faker = new Faker();
            Random random = new Random();
            Instant endInstant = Instant.now();
            Instant startInstant = endInstant.minus(7, ChronoUnit.DAYS);

            String query = "INSERT INTO api_report_log (access_time, biz_code) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < numRecords; i++) {
                long randomMillis = startInstant.toEpochMilli() + (long) (random.nextDouble() * (endInstant.toEpochMilli() - startInstant.toEpochMilli()));
                Instant randomInstant = Instant.ofEpochMilli(randomMillis);
                preparedStatement.setTimestamp(1, new java.sql.Timestamp(randomInstant.toEpochMilli()));
                preparedStatement.setInt(2, random.nextInt(3) * 200); // biz_code can be 0, 200, or 500
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("生成完成！数据已插入数据库。");
    }

    public static void main(String[] args) {
            System.out.println(10&32);
    }
}
