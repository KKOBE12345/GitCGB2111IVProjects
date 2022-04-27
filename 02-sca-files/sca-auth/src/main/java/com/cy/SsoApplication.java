package com.cy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients   //在dev001分支上实现bug修复
public class SsoApplication {

    //这是一个统一的认证中心   实现单点登录系统  SSO

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
        System.out.println("123branch go go go");
        System.out.println("dev分支进行了修改操作");
        System.out.println("dev分支又一次进行了更新操作");
    }
}
