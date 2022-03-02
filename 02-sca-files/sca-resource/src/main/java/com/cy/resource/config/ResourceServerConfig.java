package com.cy.resource.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableResourceServer
//启动方法上的权限控制
// 需要授权才可访问的方法上添加@PreAuthorize("hasAnyAuthority('sys:create')")等注解
//这其实就是一种AOP设计
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //下面注释的代码   底层会自动帮你  注入和实现  解析TOKEN
    //因此后面我就把它注释了

//    @Autowired
//    private TokenStore tokenStore;
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        //super.configure(resources);
//        //通过tokenstore获取token解析器对象  基于此对象对token进行解析
//        resources.tokenStore(tokenStore);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        //1.关闭跨域攻击
        http.csrf().disable();

        //2.关闭相关请求  对于浏览器上传文件的操作必须要进行要进行登录
        http.authorizeRequests()
                .antMatchers("/resource/upload/**")
                .authenticated().anyRequest().permitAll();

//        http.formLogin().successHandler(successHandler())
//                .failureHandler(failureHandler());
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler(){
//        return (request,response,authentication)->{
//            //构建MAP返回到客户端
//            Map<String,Object> map=new HashMap<>();
//            map.put("status",666);
//            map.put("message","恭喜你文件上传成功！！！");
//            //将MAP转换成json格式  并写到客户端
//
//            writeJsonToClient(response,map);
//
//
//
//        };
//
//    }
//
//    //定义认证失败的处理器
//    @Bean
//    public AuthenticationFailureHandler failureHandler(){
//        return (request,response,e)->{
//
//            Map<String,Object> map=new HashMap<>();
//            map.put("status",999);
//            map.put("message","很遗憾你没有权限   你的文件上传失败了！！！");
//
//            writeJsonToClient(response,map);
//
//        };
//
//    }
//
//    private void writeJsonToClient(HttpServletResponse response, Map<String,Object> map) throws IOException {
//        String json = new ObjectMapper().writeValueAsString(map);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.println(json);
//        writer.flush();
//    }


}
