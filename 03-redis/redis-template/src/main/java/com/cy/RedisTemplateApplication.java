package com.cy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //开启AOP方式的缓存应用
public class RedisTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisTemplateApplication.class, args);
    }
}
