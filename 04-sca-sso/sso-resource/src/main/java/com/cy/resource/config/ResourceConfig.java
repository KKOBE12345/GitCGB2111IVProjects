package com.cy.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-19 20:44
 **/
@Configuration
@EnableResourceServer   //资源服务器
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启动方法授权
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //关闭跨域攻击
        http.csrf().disable()
                //配置认证规则
        .authorizeRequests().antMatchers("/resource/**").authenticated()
        .anyRequest().permitAll();
    }
}
