package com.cy.system.controller;

import com.cy.system.pojo.User;
import com.cy.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-18 17:48
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login/{username}")
    public User doSelectUserByUsername(@PathVariable("username") String username){
        return userService.selectUserByUsername(username);
    };

    @GetMapping("/permission/{userId}")
    public List<String> doSelectUserPermission(@PathVariable("userId") Long userId){
        return userService.selectUserPermissions(userId);
    }
}
