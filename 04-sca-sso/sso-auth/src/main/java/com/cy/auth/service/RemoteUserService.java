package com.cy.auth.service;

import com.cy.auth.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description  远程调用接口
 * @author: George
 * @create: 2022-04-18 20:50
 **/

@FeignClient(value = "sso-system",contextId = "remoteUserService")
public interface RemoteUserService {

    @GetMapping("/user/login/{username}")
    User selectUserByUsername(@PathVariable("username") String username);

    @GetMapping("/user/permission/{userId}")
    List<String> doSelectUserPermission(@PathVariable("userId") Long userId);
}
