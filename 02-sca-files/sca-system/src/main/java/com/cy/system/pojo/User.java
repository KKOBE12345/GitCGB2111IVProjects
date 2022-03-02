package com.cy.system.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("tb_users")
@Data  //所有存储数据的对象   都应该实现序列化接口  并且添加一个序列化ID
public class User implements Serializable {
    private static final long serialVersionUID = 5417785197329856416L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String status;

}
