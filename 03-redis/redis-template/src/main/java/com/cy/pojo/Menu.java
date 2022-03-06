package com.cy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.deploy.security.ValidationState;
import lombok.Data;

import java.io.Serializable;

@TableName("tb_menus")  //mp要加的注解
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -227900179882010954L;
    @TableId(type=IdType.AUTO)
    private Long id;
    private String name;
    private String permission;

}
