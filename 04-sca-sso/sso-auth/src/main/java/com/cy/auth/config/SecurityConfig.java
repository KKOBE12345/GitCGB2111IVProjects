package com.cy.auth.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-19 09:31
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //此方法返回的对象 为后续的Oauth2提供配置
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 此方法是HTTP请求配置方法  可以在此方法中配置
     * 1.哪些资源放行（不用登陆就可以访问），不做任何配置 所有资源都可以匿名访问
     * 2.那些资源必须认证（登录）后 才可以访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();//禁用跨域攻击  假如没有禁用   那么使用第三方进行测试 会显示403
        //所有资源都必须认证
//        http.authorizeRequests()
//                .antMatchers("/**")
//                .authenticated();
        //所有资源都放行
        http.authorizeRequests().anyRequest().permitAll();

        //除了/default.html 需要认证    别的都放行
//        http.authorizeRequests().antMatchers("/default.html").authenticated()
//                .anyRequest().permitAll();
        //登录配置（去哪里认证  认证成功或失败的处理器是谁）
        //http.formLogin().defaultSuccessUrl("/index.html");  //redirect index.html
        //http.formLogin().successForwardUrl("/doIndex");     //forward


        http.formLogin().successHandler(successHandler())
                .failureHandler(failureHandler());

    }

    //定义认证成功的处理器
    //返回JSON数据
    @Bean
    public AuthenticationSuccessHandler successHandler(){
//        return new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//
//            }
//        };
        return (request,response,authentication)->{
            //构建MAP返回到客户端
            Map<String,Object> map=new HashMap<>();
            map.put("status",200);
            map.put("message","恭喜你登录成功！！！");
            //将MAP转换成json格式  并写到客户端

            writeJsonToClient(response,map);



        };

    }

    //定义认证失败的处理器
    @Bean
    public AuthenticationFailureHandler failureHandler(){

//        return new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//
//            }
//        };

        return (request,response,e)->{

            Map<String,Object> map=new HashMap<>();
            map.put("status",2000);
            map.put("message","很遗憾你登陆失败了！！！");

            writeJsonToClient(response,map);

        };

    }

    private void writeJsonToClient(HttpServletResponse response, Map<String,Object> map) throws IOException {
        String json = new ObjectMapper().writeValueAsString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
