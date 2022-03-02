package com.cy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component  //对于具体请求参数  定义黑白名单
public class RequestParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
//        String parameter = httpServletRequest.getParameter("girl");
//        return parameter;


//        String token = httpServletRequest.getHeader("player");
        String IP = httpServletRequest.getRemoteAddr();
        System.out.println(IP);
        return IP;
    }
}
