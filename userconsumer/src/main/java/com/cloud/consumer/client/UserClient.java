package com.cloud.consumer.client;

import com.cloud.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 四件事
 *  请求方式  请求路径--注解
 *  请求参数  返回值
 *
 *  采用动态代理方式生成实现类对象  然后调用   无需bean注解，直接在需求类中auwried注入
 *
 */

/**
 * feign的熔断器配置
 * 1.配置中开启feign的hystrix的熔断开关
 * 2.定义一个类  实现Userclient接口  实现方法  熔断异常逻辑
 * 3 配置fallback  类
 *
 */

//服务名称
@FeignClient(value = "user-service",fallback = UserClientImpl.class)
public interface UserClient {

    @RequestMapping("/user/{id}")
    User queryById(@PathVariable("id")Long id);
}
