package com.cy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常限流处理
 * 为什么要自定义
 * 因为默认的异常处理规则不能满足我们的要求
 *
 * */
@Component
public class ConsumerExceptionHandler implements BlockExceptionHandler {
    /**一旦服务被限流或者降级
     * sentinel底层的会调用这个方法对异常进行处理
     * */
    @Override
    public void handle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        /**
         * 方案1  对异常继续抛出
         * 方案2  对异常内容进行处理
         * 例如：返回给客户端一个看得懂的信息
         * 使用输出流对象
         * 需要借助RESPONSE对象
         * 当给客户端输出数据时，除了响应业务数据外还需要做什么？？？
         *
         * */
        //设置响应数据的编码
        httpServletResponse.setCharacterEncoding("utf-8");
        //告诉浏览器应该怎么响应
//        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter writer = httpServletResponse.getWriter();

        Map<String,Object> map=new HashMap<>();
        map.put("status", 429);

        System.out.println("哎呀呀  你这个网页又一次被限流了~~~~");
        if(e instanceof DegradeException){
//            writer.println("<h1>服务暂时不可用！！！<h1>");
            map.put("message", "服务暂时不可用！！！");
        }else{
//            writer.println("<h1>访问太频繁  稍等片刻再访问<h1>");
            map.put("message", "访问太频繁  稍等片刻再访问");
        }
        String JSON = new ObjectMapper().writeValueAsString(map);
        writer.println(JSON);
        writer.flush();  //所有的字符流都需要刷新
    }
}
