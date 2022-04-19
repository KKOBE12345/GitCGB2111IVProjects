package com.cy.auth.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-18 20:45
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1703090000172767110L;
    private Long id;
    private String username;
    private String password;
    private String status;

}
