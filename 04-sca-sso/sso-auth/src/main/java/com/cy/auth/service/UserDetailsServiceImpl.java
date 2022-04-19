package com.cy.auth.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description  构建UserDetailsService的实现类  在此类中  基于RemoteUserService接口进行
 * 远程服务调用   调用sso-system服务  获取用户数据
 * @author: George
 * @create: 2022-04-18 21:00
 **/
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.基于用户名  获取用户用户信息---Feign方式的服务调用
        com.cy.auth.pojo.User user = remoteUserService.selectUserByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("用户真的不存在呀");
        }
        //2.基于用户ID获取用户权限信息
        List<String> permissions = remoteUserService.doSelectUserPermission(user.getId());
        log.debug("permissions {}",permissions);
        //3.封装用户信息并返回
        User userDetails=new User(username,
                user.getPassword(),
                AuthorityUtils.createAuthorityList(permissions.toArray(new String[]{})));

        return userDetails;//交给Spring Security的认证中心  来进行比对
    }
}
