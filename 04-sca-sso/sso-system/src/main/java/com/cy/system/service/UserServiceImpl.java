package com.cy.system.service;

import com.cy.system.dao.UserMapper;
import com.cy.system.pojo.User;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;  //这里采用构造注入

    @Override
    public User selectUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("username can not be null");
        }
        return userMapper.selectUserByUsername(username);
    }

    @Cacheable(value = "permissionCache")
    @Override
    public List<String> selectUserPermissions(Long userId) {
        return userMapper.selectUserPermissions(userId);
    }
}
