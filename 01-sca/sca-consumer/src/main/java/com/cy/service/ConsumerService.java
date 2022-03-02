package com.cy.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    /**@SentinelResource
     * 描述的方法会作为限流链路中的叶子节点
     * */
    @SentinelResource
    public String  doGetResource(){
        //。。。这里后续可以写对数据库资源的访问
        return "get resource!!!";
    }
}