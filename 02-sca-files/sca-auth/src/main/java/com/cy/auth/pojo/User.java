package com.cy.auth.pojo;

import lombok.Data;

import java.io.Serializable;

/*通过此方式封装User对象*/

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5417785197329856416L;
    private Long id;
    private String username;
    private String password;
    private String status;
}
