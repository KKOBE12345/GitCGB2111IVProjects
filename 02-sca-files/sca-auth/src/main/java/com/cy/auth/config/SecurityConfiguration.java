package com.cy.auth.config;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 此类中要配置
 * 1.加密对象
 * 2.配置认证规则
 * 在我们执行登陆操作时  底层逻辑
 * 1.filter
 * 2.authenticationManager
 * 3.authenticationProvider
 * 4.UserDetailService(负责用户信息的获取和封装)
 * */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //上面这是典型的适配器模式

    //初始化加密对象
    //此对象提供一种不可逆的加密方式   比MD5更加安全
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 定义认证管理器对象  这个对象负责用户信息的认证
     * 判定用户身份信息的合法性   在基于OAUTU2完成认证时
     * 需要此对象   将这个对象交给SPRING管理*/

    //nacos
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    };


    /**在这个方法中  可以配置认证规则*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
// 这句话注释了 就是默认放行所有的请求了  原本的意思是所有的路径都需要认证

        //1.关闭跨域攻击   这个一定要打开
        http.csrf().disable();

        //http.authorizeRequests().antMatchers("/***/****").authenticated();
        // 上面的意思是   必须认证以后才可以访问
        //比如添加购物车的时候 必须要登录！！！！

        //2.放行所有资源的访问（也可以就放行登录操作）   系统底层会对登录 请求放行
        http.authorizeRequests().anyRequest().permitAll();
        //3.定义登录成功和失败以后的处理逻辑   http.formLogin()会对外暴露一个登录路径
        http.formLogin().successHandler(successHandler())
        .failureHandler(failureHandler());
//        http.formLogin();
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
            map.put("status",2324);
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
            map.put("status",2423);
            map.put("message","很遗憾你登陆失败了！！！");

            writeJsonToClient(response,map);

        };

    }

    private void writeJsonToClient(HttpServletResponse response,Map<String,Object> map) throws IOException {
        String json = new ObjectMapper().writeValueAsString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
    }
}
