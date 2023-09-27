package com.a02.sfams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigurer implements  WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义排除swagger访问的路径配置
        String[] swaggerExcludes=new String[]{"/swagger-ui.html","/swagger-resources/**","/webjars/**"};
        //自定义去除的路径
        String[] myExcludes=new String[]{"/user/login"};
        registry.addInterceptor(new RefreshTokenInterceptor())
                // addPathPatterns 用于添加拦截规则
                .addPathPatterns("/**")
                //自己定义的不拦截的规则
                .excludePathPatterns(myExcludes)
                //去除拦截springboot的静态文件
                .excludePathPatterns("/html/*")
                .excludePathPatterns("/demo")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                //下面是固定格式,如果不配置swagger页面将会访问不了
                .excludePathPatterns(swaggerExcludes);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
