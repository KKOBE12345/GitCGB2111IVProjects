package com.cy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
@EnableCaching   //启动缓存机制
public class SystemApplication {
    public static void main(String[] args) {

        SpringApplication.run(SystemApplication.class, args);
    }
}
