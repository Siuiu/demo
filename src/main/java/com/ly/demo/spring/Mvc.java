package com.ly.demo.spring;

import cn.hutool.core.map.MapUtil;

import java.util.HashMap;

/**
 * @Author liuyang
 * @Date 2023/7/18 20:22
 **/
class DispatcherServlet {

    public static void main(String[] args) {
        //1.发送请求
        String handler = DispatcherServlet.sendHttp("/hello");
        //3.将handler交给HandlerAdapter处理
        String modelAndView = HandlerAdapter.callHandler(handler);
        //5.将modelAndView交给识图解析器解析
        String view = ViewResolver.viewResolver(modelAndView);
        //6.交给前端渲染视图
        System.out.println("前端渲染" + view);
    }

    //2.从HandlerMapping中拿到请求映射关系的handler
    private static String sendHttp(String req) {
        return HandlerMapping.get(req);
    }
}

class HandlerMapping {
    private static HashMap<String, String> HandleMapping = MapUtil.of("/hello", "xxxController");

    public static String get(String req) {
        return HandleMapping.get(req);
    }
}

class HandlerAdapter {
    private static HashMap<String, String> handlerAdapter = MapUtil.of("xxxController", "ModelAndView");

    //4.HandlerAdapter调用对应的controller,获取ModelAndView
    public static String callHandler(String handler) {
        return call(handler);
    }

    private static String call(String handler) {
        return handlerAdapter.get(handler);
    }

}

class ViewResolver {

    public static String viewResolver(String modelAndView) {
        return "hello" + modelAndView;
    }
}