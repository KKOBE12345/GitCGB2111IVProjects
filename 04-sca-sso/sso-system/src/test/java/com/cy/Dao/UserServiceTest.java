package com.cy.Dao;

import com.cy.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-18 17:22
 **/
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void selectPermissions(){
        List<String> strings = userService.selectUserPermissions(1L);
        System.out.println(strings);
        userService.selectUserPermissions(1L);
        System.out.println(strings);
    }
}
