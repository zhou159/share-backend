package com.share;

import com.share.config.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.security.auth.login.Configuration;

@MapperScan("com.share.mapper")
@SpringBootApplication
public class ShareApplication {

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(ShareApplication.class);
//        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
//        WebSocketServer.setApplicationContext(configurableApplicationContext);

        SpringApplication.run(ShareApplication.class, args);
    }

}
