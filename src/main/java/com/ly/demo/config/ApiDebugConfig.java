package com.ly.demo.config;

import cn.hutool.core.collection.CollUtil;
import com.ly.demo.entity.ApiDebugEntity;
import com.ly.demo.entity.InterfaceEntity;
import com.ly.demo.service.impl.ApiDebugServiceImpl;
import com.ly.demo.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author liuyang
 * @Date 2023/6/25 17:04
 **/
@Component
@Slf4j
public class ApiDebugConfig {
    @Resource
    private WebApplicationContext applicationContext;
    @Resource
    private ApiDebugServiceImpl apiDebugService;

    @PostConstruct
    public void executeSharedLogic() {
        List<ApiDebugEntity> apiDebugEntityList = new ArrayList<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        for (RequestMappingInfo info : methodMap.keySet()) {
            ApiDebugEntity apiDebugEntity = new ApiDebugEntity();
            //1.获取方法名
            apiDebugEntity.setInterfaceName(info.getName());
            //2.获取path
            List<PathPattern> pathPatterns = new ArrayList<>(info.getPathPatternsCondition().getPatterns());
            String url = pathPatterns.get(0).getPatternString();
            apiDebugEntity.setInterfacePath(url);
            //排除默认的请求
            if ("/error".equals(url)) {
                log.info(url);
                continue;
            }

            //3.获取接口类型
            List<RequestMethod> types = new ArrayList<>(info.getMethodsCondition().getMethods());
            //如果是使用@RequestMapping修饰的默认当做post
            if (CollUtil.isEmpty(types)) {
                types.add(RequestMethod.POST);
            }
            apiDebugEntity.setRequestType(types.get(0).name());

            //4.获取参数列表
            HandlerMethod handlerMethod = methodMap.get(info);
            Method method = handlerMethod.getMethod();
            Class<?> beanType = handlerMethod.getBeanType();
            String paramList = CommonUtil.getParamList(beanType.getName(), method.getName());
            apiDebugEntity.setRequestParam(paramList);
            apiDebugEntityList.add(apiDebugEntity);
        }
        apiDebugService.addInterface(apiDebugEntityList);
    }
}
