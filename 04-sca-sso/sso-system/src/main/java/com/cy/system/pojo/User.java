package com.cy.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-17 18:11
 **/

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@TableName("tb_users")  加入SQL语句自己写   不需要指定表名
public class User implements Serializable {

    private static final long serialVersionUID = 8810496131105575888L;
    private Long id;
    private String username;
    private String password;
    private String status;

}
