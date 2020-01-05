package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =  registry.addInterceptor(testInterceptor);
        //将这个controller放行
        registration.excludePathPatterns("/error");
        //登录不拦截
        registration.excludePathPatterns("/login/user");
        //登录不拦截
        registration.excludePathPatterns("/login/index");
        //拦截全部
        registration.addPathPatterns("/**");
    }
}