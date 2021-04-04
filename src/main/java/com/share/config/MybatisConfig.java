package com.share.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Description: sql语句显示以及其他信息剔除，方便开发时调试显示sql语句
 * */
@Configuration
public class MybatisConfig {
    @Bean
    public PerformanceInterceptor performanceMonitorInterceptor(){
        return new PerformanceInterceptor();
    }
}
