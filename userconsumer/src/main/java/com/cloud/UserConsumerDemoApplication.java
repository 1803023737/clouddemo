package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * hystrix 集成步骤
 * 1加入依赖
 * 2 加上注解@EnableHystrix 或者@EnableCircuitBreaker（还包括服务熔断）
 * 3 加入配置  超时时长配置
 * 4@EnableCircuitBreaker @EnableDiscoveryClient @SpringBootApplication  可以集成为一个注解@SpringCloudApplication  springboot+熔断器+负载均衡器
 *
 */

/**
 * feign
 * 1.依赖
 * 2.@EnableFeignClients
 * 3.配置  无需配置
 * 4.编写 client
 */
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients//引入feign依赖
public class UserConsumerDemoApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        // 这次我们使用了OkHttp客户端,只需要注入工厂即可
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerDemoApplication.class, args);
    }
}