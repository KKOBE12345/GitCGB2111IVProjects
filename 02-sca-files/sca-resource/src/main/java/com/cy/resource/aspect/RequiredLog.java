package com.cy.resource.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解  痛过此注解描述需要执行拓展业务逻辑的方法
 * 简单点就是做个标识*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredLog {
    String value() default "";
    //....后面可以自己定义属性
}
