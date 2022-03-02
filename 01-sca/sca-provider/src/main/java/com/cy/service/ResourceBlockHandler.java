package com.cy.service;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResourceBlockHandler {
    public static String doHandle24(Integer id, BlockException e){
        log.info("你这个热点资源被限流了", e);
        return "你对三上悠亚的访问太频繁了~~~";
    }
}
