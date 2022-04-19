package com.cy.system.dao;

import com.cy.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-17 20:21
 **/
@Mapper
public interface UserMapper {

    @Select("select id,username,password,status "+
            "from tb_users where username=#{username}")
    User selectUserByUsername(String username);

    //基于用户ID  查询用户的权限
    @Select("select distinct m.permission from tb_user_roles ur join tb_role_menus rm\n" +
            "on ur.role_id=rm.role_id join tb_menus m on rm.menu_id=m.id\n" +
            "where ur.id=#{userId}")
    List<String> selectUserPermissions(Long userId);
}
