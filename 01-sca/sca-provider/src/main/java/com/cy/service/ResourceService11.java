package com.cy.service;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ResourceService11 {

    @SentinelResource(value = "resource",
            blockHandlerClass = ResourceBlockHandler.class,
    blockHandler = "doHandle24")
    public String doSentinel026(Integer id){
        return "kobekobekobekobekobekobe!!!"+id;
    }
}
