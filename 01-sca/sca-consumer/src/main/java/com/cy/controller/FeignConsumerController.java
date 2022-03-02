package com.cy.controller;


import com.cy.service.ConsumerService;
import com.cy.service.RemoteProviderService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class FeignConsumerController {

    @Autowired
    private RemoteProviderService remoteProviderService;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/sentinel")
    public String doRestEcho02(){

        return consumerService.doGetResource();

    }

    @GetMapping("/kobe123/{kkk}")
    public String doRestEcho(@PathVariable("kkk") String jordan){

        return remoteProviderService.acceptEcho(jordan);

    }
}
