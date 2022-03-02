package com.cy.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.cluster.request.Request;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class DefaultRequestOriginParse implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        //参数来定义黑白名单
//        return request.getParameter("player");

        //ip地址来定义黑白名单
//        String ip = request.getRemoteAddr();
//        System.out.println(ip);
//        return ip;

        //请求头来定义黑白名单
        String token = request.getHeader("token");
        return token;
    }
}
