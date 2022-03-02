package com.cy.auth.service;


import com.cy.auth.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "sca-system",
        contextId = "remoteUserService")
public interface RemoteUserService {

    @GetMapping("/user/login/{username}")
    User SelectUserByUsername(@PathVariable("username") String username);

    @GetMapping("/user/permission/{userId}")
    List<String> SelectUserPermission(@PathVariable("userId") Long userID);


}

