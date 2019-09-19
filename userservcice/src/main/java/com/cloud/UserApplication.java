package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
扫描mapper文件夹并实例化实现类对象到spring容器中
 *  因为注册中西你可能会有多种  这边注解是EnableDiscoveryClient  @EnableEurekaClient只兼容eureka
 *
 *  注册客户端步骤  1.引入依赖  2 打上注解  3 加上配置
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.cloud.userservice.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);

    }

}
