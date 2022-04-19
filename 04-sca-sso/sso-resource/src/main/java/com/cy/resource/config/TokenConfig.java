package com.cy.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-19 16:19
 **/
@Configuration
public class TokenConfig {


    /**
     * 创建令牌存储对象
     * @return
     */
    @Bean
    public TokenStore tokenStore() {

        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt令牌增强器   可以通过此对象创建JWT令牌  是普通令牌的增强版
     * 普通令牌是不可以存用户信息的
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
        //设置签名KEY  对JWT令牌进行签名时使用  这个KEY只能服务端知道
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }
    //这个签名KEY 可以自己定义 一般是比较复杂的
    private String signingKey="auth";
}
