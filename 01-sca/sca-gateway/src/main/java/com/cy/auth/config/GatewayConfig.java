package com.cy.auth.config;


import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GatewayConfig {
    public GatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                //定义要响应的数据
                Map<String,Object> map=new HashMap<>();
                map.put("status", 42999999);
                map.put("message", "you too many requests~~~");

                //封装响应数据并返回

                String jsonStr = JSON.toJSONString(map);  //这个JSON是阿里巴巴写的


                return ServerResponse.ok().     //构建一个builder对象
                        body(Mono.just(jsonStr),   //构建MONO对象
                        String.class);
            }
        });

    }
}
