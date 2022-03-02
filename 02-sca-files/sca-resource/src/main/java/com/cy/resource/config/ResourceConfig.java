package com.cy.resource.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration  //在过滤器的层面实现跨域配置！！！
public class ResourceConfig {
    @Bean   //定义跨域过滤器的配置  并且注册到SPRING容器中
    public FilterRegistrationBean<CorsFilter>
            filterFilterRegistrationBean(){
        UrlBasedCorsConfigurationSource configurationSource=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean<CorsFilter> fBean=new FilterRegistrationBean<>(
                new CorsFilter(configurationSource)
        );
        fBean.setOrder(Integer.MIN_VALUE);
        return fBean;
    }
}
