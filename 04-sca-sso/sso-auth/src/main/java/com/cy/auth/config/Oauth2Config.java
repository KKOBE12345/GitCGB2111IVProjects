package com.cy.auth.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * Oauth2是一种协议 或者 规范  定义了用户身份认证和授权的方式
 * 基于密码进行认证  基于指纹进行认证  基于第三方进行认证  但是具体完成需要有一组对象
 * 0.数据资源  （数据库 文件系统）
 * 1.资源服务器（负责访问资源    商品  订单   库存  会员。。。）
 * 2.认证服务器（负责完成用户身份的认证）
 * 3.客户端对象（表单  令牌）
 * 4.资源拥有者
 *
 * 在Oauth2规范下  怎么对用户进行认证
 * 1.认证的地址
 * 2.用户携带什么信息进行认证
 * 3.具体完成认证的对象是谁
 * @author: George
 * @create: 2022-04-19 13:40
 **/
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //这边定义认证的地址（客户端URL中输入的PATH）
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        super.configure(security);
        security
                //(   /oauth/token   )
                .tokenKeyAccess("permitAll()")
                //公开校验令牌的地址(   /oauth/check_token   )
                .checkTokenAccess("permitAll()")
                //允许通过表单的方式进行认证
                .allowFormAuthenticationForClients();
    }

    //这边定义用户认证时  需要携带什么信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        clients.inMemory()
                //客户端标识
                .withClient("gateway-client")
                //客户端密钥
                .secret(passwordEncoder.encode("123456"))
                //指定认证类型
                .authorizedGrantTypes("password","refresh_token")
                //作用域 满足以上条件的所有客户端都可以来这里进行认证
                .scopes("all");
    }

//定义由谁来完成认证
    //认证成功后由怎么样的形式来颁发令牌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        super.configure(endpoints);
        //定义由谁完成认证
        endpoints.authenticationManager(authenticationManager);
        //定义 用户状态信息的存储（存到MYSQL  REDIS  JWT  内存）
        endpoints.tokenEnhancer(jwtAccessTokenConverter);
//        endpoints.tokenStore(tokenStore());
        //定义令牌业务（自己指定令牌规则）
        endpoints.tokenServices(tokenService());
        //允许客户端认证的请求方式(默认只支持POST)
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }

    /**
     * 构建 TOKEN service对象  用于处理token业务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //令牌存储
        tokenServices.setTokenStore(tokenStore);
        //令牌增强  由普通令牌 改为JWT令牌
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        //令牌有效期
        tokenServices.setAccessTokenValiditySeconds(3600);
        //配置可以刷新令牌
        tokenServices.setSupportRefreshToken(true);
        //配置刷新令牌有效期（比访问令牌时间长一点）
        tokenServices.setRefreshTokenValiditySeconds(7200);
        return tokenServices;

    }


}
