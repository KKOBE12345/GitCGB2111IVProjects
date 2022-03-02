package com.cy.system.service;

import com.cy.system.dao.UserMapper;
import com.cy.system.pojo.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;  //这里采用构造注入

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public List<String> selectUserPermissions(Long userId) {
        return userMapper.selectUserPermissions(userId);
    }
}
