package com.cy.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select id,username,password,status "+
    "from tb_users where username=#{username}")
    User selectUserByUsername(String username);

    //基于用户ID  查询用户的权限
    @Select("select distinct m.permission from tb_user_roles ur join tb_role_menus rm\n" +
            "on ur.role_id=rm.role_id join tb_menus m on rm.menu_id=m.id\n" +
            "where ur.id=#{userId}")
    List<String> selectUserPermissions(Long userId);

}
