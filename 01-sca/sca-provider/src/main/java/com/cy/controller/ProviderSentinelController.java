package com.cy.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.service.ResourceService11;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
@RequestMapping("/provider")
public class ProviderSentinelController {

    @Autowired
    private ResourceService11 resourceService;


    private AtomicLong atomicLong = new AtomicLong(1);


    @GetMapping("/sentinel023")
    public String doSentinel23() throws InterruptedException {

        long count = atomicLong.getAndIncrement();
        if(count%5==4){
//            Thread.sleep(5000);
            throw  new NullPointerException();
        }
        return "sentinel 2323232323";
    }

    @GetMapping("/sentinel024")
//    @SentinelResource("resource")
    public String doSentinel24(@RequestParam("id") Integer id){
        return "GET resourceById  "+id;
    }

    @GetMapping("/sentinel025")
//    @SentinelResource("resource")
    public String doSentinel25(@RequestParam("id") Integer id){
        return resourceService.doSentinel026(id);
    }

    @GetMapping("/sentinel026")
//    @SentinelResource("resource")
    public String doSentinel26(@RequestParam("girl") String girl){
        return "凌晨四点的洛杉矶"+girl;
    }
}
