package com.cy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 日志级别：trace  debug  info  warn  error
 * */
@RestController
@Slf4j
@RefreshScope
public class ProviderController {
    @Value("${page.pageSize:90}")
    private Integer james02;

    @Value("${server.port:8080}")
    private String kobe;

    @Value("${server.tomcat.threads.max:888}")
    private Integer james;


//    @Value("${logging.level.com.cy}")
//    private String logLevel;

    @GetMapping("/provider/thread")
    public String getMaxThread(){
        return "现在的最大线程数是："+james+"现在的页面大小是："+james02;
    }


    @GetMapping("/provider/echo123/{mmm}")
    public String word(@PathVariable("mmm") String kobeeee) throws InterruptedException {
//        System.out.println(logLevel);
//            Thread.sleep(5000);
        log.info("哈哈哈开始的时间是：{}",System.currentTimeMillis());
        return kobe+"第一次使用微服务好紧张！！！"+kobeeee;

    }

}
