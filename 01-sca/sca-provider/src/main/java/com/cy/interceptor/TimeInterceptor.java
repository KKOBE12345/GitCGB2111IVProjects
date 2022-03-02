package com.cy.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;


/**
 * 自定义一个拦截器
 * 在CONTROLLER方法执行之前执行
 * 可以根据方法的返回值  决定是否放行
 * */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器开始拦截了！！！你看我的return  true才放行");
        int hour = LocalTime.now().getHour();
        System.out.println("现在的时间是："+hour);
        if(hour<9||hour>20){
                throw new  RuntimeException("请在允许的时间访问！！！");
        }
        return true;
    }
}
