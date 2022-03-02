package com.cy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SsoApplication {

    //这是一个统一的认证中心   实现单点登录系统  SSO

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
