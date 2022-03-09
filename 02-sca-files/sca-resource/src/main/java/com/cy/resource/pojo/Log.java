package com.cy.resource.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Log implements Serializable {
    private static final long serialVersionUID = 5457824815445526090L;
    private Long id;
    /**登录用户*/
    private String username;
    /**客户端的ip地址*/
    private String ip;
    /**执行操作的时间*/
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
}
