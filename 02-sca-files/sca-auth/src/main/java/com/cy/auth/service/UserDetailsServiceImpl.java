package com.cy.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl
        implements UserDetailsService {
    /**
     * username来自客户端
     * 然后获取数据库中的用户信息
     * */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //1.基于用户名查询用户信息（暂时先给假数据）
        //现在给真数据  数据库查询到的  基于Feign方式获取远程数据并封装
        com.cy.auth.pojo.User info = remoteUserService.SelectUserByUsername(username);
        if(info==null){
            log.error("用户不存在 {}", username);
            throw new UsernameNotFoundException("没有这样的用户！！");
        }
//        String encodePassword=//假设这个密码来自于数据库
//                passwordEncoder.encode("526952");
//        String encodePassword=passwordEncoder.encode(info.getPassword());
        //2.封装用户数据并返回（）用户名  密码  权限信息

//        return new User(username, encodePassword,//这是已经加密的密码
//                AuthorityUtils.createAuthorityList(
//                        "sys:res:create"//这里的权限后面再考虑
//                                    ,"sys:res:retrieve")

        //这是根据数据库查到的用户的权限
        List<String> permissions = remoteUserService.SelectUserPermission(info.getId());
        log.info("permission is {}", permissions);
        return new User(info.getUsername(), info.getPassword(),//这是已经加密的密码
                AuthorityUtils.
                        createAuthorityList(permissions.toArray(new String[]{})));
    }
}
