package com.cy.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-19 20:33
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController {
    /**
     * @PreAuthorize 注解描述的方法是一个权限切入点方法，
     * 访问此方法时需要授权才可以访问
     * @RequriedLog 注解描述的方法为一个日志切入点方法，
     * 访问此方法时要进行日志记录。
     * @return
     */
//    @RequiredLog("查询资源")
    @PreAuthorize("hasAuthority('sys:res:list')")
    @GetMapping
    public String doSelect(){
        //访问service/mapper/db
        return "select resource";
    }

    @PreAuthorize("hasAuthority('sys:res:create')")
    @PostMapping
    public String doCreate(){
        return "create resource";
    }

    @PreAuthorize("hasAuthority('sys:res:update')")
    @PutMapping
    public String doUpdate(){
        return "update resource";
    }
    @DeleteMapping
    public String doDelete(){
        return "delete resource";
    }
}
