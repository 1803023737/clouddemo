package com.cloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 1.引入eureka依赖
 * 2.加上注解  EnableEurekaServer 启动eureka服务
 * 3.设置好服务的端口
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class);
    }

}
