package com.share.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * minio的参数配置，从配置文件获取属性值
 * */

@Data
@Component
public class MinioConfig {
    @ApiModelProperty("域名，IP地址")
    @Value("${minio.endpoint}")
    private String endpoint;

    @ApiModelProperty("端口")
    @Value("${minio.port}")
    private int port;

    @Value("${minio.accessKey}")
    @ApiModelProperty("minio的登录名")
    private String accessKey;

    @Value("${minio.secretKey}")
    @ApiModelProperty("minio的密码")
    private String secretKey;

    @Value("${minio.secure}")
    @ApiModelProperty("如果是true，则用的是https而不是http,默认值是true")
    private boolean secure;

    @Value("${minio.configDir}")
    @ApiModelProperty("服务器minio，data所在目录")
    private String configDir;

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient minioClient = new MinioClient(endpoint,port,accessKey, secretKey, secure);
        return minioClient;
    }
}
