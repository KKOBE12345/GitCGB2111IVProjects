package com.cy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProviderExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        Map<String,Object> map=new HashMap<>();
        map.put("status", 429);
        System.out.println("哎呀呀  么么哒~~~~");
        if(e instanceof DegradeException){
            map.put("message", "服务被降级了   大桥未久暂时不可用了呢！！！");
        }else if(e instanceof AuthorityException){
            map.put("message", "访问太频繁 魔人布欧");
        }else{
            map.put("message", "哈哈哈哈哈哈又出异常了");
        }
        String JSON = new ObjectMapper().writeValueAsString(map);
        writer.println(JSON);
        writer.flush();  //所有的字符流都需要刷新
        writer.close();
    }
}
