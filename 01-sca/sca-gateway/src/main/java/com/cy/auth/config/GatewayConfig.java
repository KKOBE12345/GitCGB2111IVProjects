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
                map.put("message", "you too many request~~~");

                //封装响应数据并返回

                Object jsonStr = JSON.toJSON(map);


                return ServerResponse.ok().body(Mono.just(jsonStr),
                        String.class);
            }
        });

    }
}
