package com.cy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }

    @RestController
    @Slf4j
    @RefreshScope   //告诉SPRING一旦配置 改变   就自动变化
    public static class ProviderController{
        @Value("${server.port:8080}")
        private String kobe;


        @Value("${logging.level.com.cy:error}")
        private String logLevel;


        @GetMapping("/provider/echo/{mmm}")
        public String word(@PathVariable("mmm") String kobeeee) throws InterruptedException {
            System.out.println(logLevel);
//            Thread.sleep(5000);
            log.info("哈哈哈开始的时间是：{}",System.currentTimeMillis());
            return kobe+"凌晨四点的洛杉矶！！么么哒！！！"+kobeeee;

        }
    }
}
