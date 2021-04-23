package com.share.config;

//import com.share.interceptor.LoginInterceptor;
//import com.share.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
//    @Autowired
//    LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        List<String> asList = Arrays.asList("/user/loginusername", "/user/logintel", "/user/registerUsername" ,"/user/registertel","/user/verifyCode","/rent/**");
//        registry.addInterceptor(loginInterceptor).excludePathPatterns(asList);
//    }
}
