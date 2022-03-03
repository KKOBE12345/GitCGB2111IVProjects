package com.cy.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Component
public class BlackUrlGlobalFilter implements GlobalFilter, Ordered {
    public static List<String> blackUrl=new ArrayList<>();

//    public BlackUrlGlobalFilter() {
//
//        blackUrl.add("/nacos/kobejames/provider/echo5269/23");
//        blackUrl.add("/nacos/kobejames/provider/echo5269/24");
//    }

    public void black(){
        blackUrl.add("/nacos/kobejames/provider/echo5269/23");
        blackUrl.add("/nacos/kobejames/provider/echo5269/24");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**在这个集合中的url不允许访问我们的资源
         * */
        black();
        String path = exchange.getRequest().getURI().getPath();
        System.out.println(path);
        ServerHttpResponse response = exchange.getResponse();
        if(blackUrl.contains(path)){
            throw new RuntimeException("这个请求已经不可以访问了");
        }

        return chain.filter(exchange);
    }

    /**order是定义过滤 过滤器的优先级
     * 数字越小 优先级越高
     * */
    @Override
    public int getOrder() {
        return -99;
    }
}
