package com.ly.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.ly.demo.entity.*;
import com.ly.demo.service.UserService;
import com.ly.demo.service.impl.ApiDebugServiceImpl;
import com.ly.demo.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private UserService service;
    private static UserService userService;
    @Resource
    private ApiDebugServiceImpl apiDebugService;

    @PostMapping(value = "/hello",name = "哈喽")
    public SmResult hello(@Valid @RequestBody UserEntity user) {
        return userService.getList();
    }

    @GetMapping("/VerificationCode")
    public void imageCode(HttpSession session, HttpServletResponse response) throws IOException {
        GifCaptcha shearCaptcha = CaptchaUtil.createGifCaptcha(300, 100, 4);
        //获取到4位数的验证码
        String code = shearCaptcha.getCode();
        log.info("验证码--->{}", code);
        ServletOutputStream sos = response.getOutputStream();
        shearCaptcha.write(sos);
    }

    @GetMapping("/head")
    public void head(@RequestBody UserVo vo,@RequestParam String like) {
        System.out.println(like);
        log.info("uid--->{}", JSONUtil.toJsonStr(vo));

    }

    @PostConstruct
    public void init() {
        userService = this.service;
    }

    @RequestMapping("/getValue")
    public String testValue() {
        return userService.getValue();
    }

    @Autowired
    WebApplicationContext applicationContext;

    @GetMapping("/getInterfacePath")
    public List<InterfaceEntity> getInterfacePath() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<InterfaceEntity> objects = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()) {//获取请求路径
            List<PathPattern> pathPatterns = new ArrayList<>(info.getPathPatternsCondition().getPatterns());
            List<RequestMethod> types = new ArrayList<>(info.getMethodsCondition().getMethods());
            String url = pathPatterns.get(0).getPatternString();
            if ("/error".equals(url)) {
                log.info(url);
                continue;
            }
            if (CollUtil.isEmpty(types)) {
                //@RequestMapping("/getValue")修饰的路径不会显示请求方式
                types.add(RequestMethod.POST);
            }
            //获取全部请求名称
            objects.add(new InterfaceEntity().setUrl(url).setType(types.get(0).name()));
        }
        return objects;
    }

    @GetMapping("/gptGetInterfacePath")
    public List<InterfaceEntity> gptGetInterfacePath() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<InterfaceEntity> objects = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()) {
            InterfaceEntity interfaceEntity = new InterfaceEntity();
            List<PathPattern> pathPatterns = new ArrayList<>(info.getPathPatternsCondition().getPatterns());
            List<RequestMethod> types = new ArrayList<>(info.getMethodsCondition().getMethods());
            String url = pathPatterns.get(0).getPatternString();

            //排除默认的请求
            if ("/error".equals(url)) {
                log.info(url);
                continue;
            }

            //如果是使用@RequestMapping修饰的
            if (CollUtil.isEmpty(types)) {
                types.add(RequestMethod.POST);
            }
            interfaceEntity.setUrl(url).setType(types.get(0).name());

            //参数列表
            HandlerMethod handlerMethod = methodMap.get(info);
            System.out.println("=====" + handlerMethod.toString());
            List<String> excludeClass = Arrays.asList("javax.servlet.http.HttpSession", "javax.servlet.http.HttpServletResponse");
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            List<ParameterEntity> parameters = Arrays.stream(methodParameters).filter(parameter -> !excludeClass.contains(parameter.getParameterType().getName()))
                    .map(parameter -> {
                        ParameterEntity parameterEntity = new ParameterEntity().setName(parameter.getParameterName());
                        Class<?> parameterType = parameter.getParameterType();
                        if (parameterType.isPrimitive() || parameterType.equals(String.class)) {
                            parameterEntity.setType(parameterType.getName());
                        } else {
                            Object classStruct = CommonUtil.getClassStruct(parameterType.getName());
                            parameterEntity.setType(classStruct);
                        }
                        return parameterEntity;
                    })
                    .collect(Collectors.toList());
            interfaceEntity.setParameters(parameters);
            objects.add(interfaceEntity);
        }
        return objects;
    }

    @GetMapping("/getInterfacePathDemo03")
    public List<InterfaceEntity> getInterfacePathDemo03() {
        List<ApiDebugEntity> apiDebugEntityList = new ArrayList<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<InterfaceEntity> objects = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()) {
            ApiDebugEntity apiDebugEntity = new ApiDebugEntity();
            List<PathPattern> pathPatterns = new ArrayList<>(info.getPathPatternsCondition().getPatterns());
            List<RequestMethod> types = new ArrayList<>(info.getMethodsCondition().getMethods());
            String url = pathPatterns.get(0).getPatternString();

            //排除默认的请求
            if ("/error".equals(url)) {
                log.info(url);
                continue;
            }

            //如果是使用@RequestMapping修饰的
            if (CollUtil.isEmpty(types)) {
                types.add(RequestMethod.POST);
            }
            apiDebugEntity.setInterfacePath(url).setRequestType(types.get(0).name());

            //参数列表
            HandlerMethod handlerMethod = methodMap.get(info);
            Method method = handlerMethod.getMethod();
            Class<?> beanType = handlerMethod.getBeanType();
            String paramList = CommonUtil.getParamList(beanType.getName(), method.getName());
            log.info("=======================paramList:{}", paramList);
            apiDebugEntity.setRequestParam(paramList);
            apiDebugEntityList.add(apiDebugEntity);
        }
        apiDebugService.addInterface(apiDebugEntityList);
        return objects;
    }


}
