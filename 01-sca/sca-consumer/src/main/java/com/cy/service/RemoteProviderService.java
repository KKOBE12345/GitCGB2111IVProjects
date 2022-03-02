package com.cy.service;


import com.cy.service.factory.ProviderCallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sca-provider",contextId = "remoteProviderService",fallbackFactory = ProviderCallbackFactory.class)
public interface RemoteProviderService {


    @GetMapping("/provider/echo123/{mmm}")
    public String acceptEcho(@PathVariable("mmm") String kobe);
}
