package com.ly.demo.test;

import cn.hutool.core.io.FileUtil;
import com.ly.demo.service.UserService;
import com.ly.demo.vo.DownloadTaskDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * @Author liuyang
 * @Date 2022-10-26 17:32
 **/
@Component
@Slf4j
public class Test {
    private static UserService u;
    private final static String CAS_URL = "https://imc-khcymjxj.betatest.huaweioneaccess.com/api/v1/cas/login";
    private final static String OAUTH_URL = "https:zz///imc-czraddpg.betatest.huaweioneaccess.com/api/v1/oauth2/authorize";


    public static void main(String[] args) throws IOException {

    }

}