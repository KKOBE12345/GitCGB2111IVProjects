package com.cy.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_logs")
public class Log implements Serializable {
    private static final long serialVersionUID = 3054471551801044482L;
    @TableId(type = IdType.AUTO)
    private Long id;
    /**登录用户*/
    private String username;
    /**客户端的ip地址*/
    private String ip;
    /**执行操作的时间*/
    @TableField("createdTime")
    private Date createdTime;
    /**执行的操作名*/
    private String operation;
    /**方法的方法:这里要写类全名.方法名*/
    private String method;
    /**执行方法时传递的实际参数是什么*/
    private String params;
    /**执行这个业务的耗时*/
    private Long time;
    /**业务执行过程是否成功了*/
    private Integer status;
    /**可能出现的错误信息*/
    private String error;
}