package com.share.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description: swagger接口文档配置，便于前后端协同开发
 * */

@Configuration
@EnableSwagger2               //开启swagger
public class SwaggerConfig {

    //配置swagger的docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("share")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.share.controller"))
                .build();
    }

    //配置swagger信息apiinfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("share","localhost:8080","1595904478@qq.com");
        return new ApiInfo("社区分享系统SwaggerAPI文档",
                "share！",
                "1.0",
                "http://localhost:8080/index.html",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",

                new ArrayList()
        );
    }
}
