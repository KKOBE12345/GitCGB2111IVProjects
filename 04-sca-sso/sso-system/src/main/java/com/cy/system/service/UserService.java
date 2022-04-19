package com.cy.system.service;

import com.cy.system.pojo.User;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-18 17:10
 **/
public interface UserService {
    /**基于用户名查询用户信息
     * 这个信息交给认证中心
     * 然后认证中心进行比对认证
     * */

    User selectUserByUsername(String username);

    /**基于登录用户ID查询用户权限*/
    List<String> selectUserPermissions(Long userId);

}
