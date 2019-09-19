package com.cloud.consumer.dao;

import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer.client.UserClient;
import com.cloud.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * DefaultProperties  设置默认配置  全局统一的服务降级熔断处理
 */
@Component
@DefaultProperties(defaultFallback = "Fallback")
public class UserDao {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发现客户端对象
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 采用负载均衡方式
     */
    private RibbonLoadBalancerClient client;


    /**
     * 根据服务id  动态获得服务实例的ip和端口
     * @param id
     * @return
     */
/*    public User queryUserById(Long id){
        //第一种方式
        //根据服务的id  拉取服务的host和端口非负载均衡方式
         //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //获得服务的实例  就是服务的实际节点
     *//*   if (instances!=null && instances.size()>0){
            ServiceInstance serviceInstance = instances.get(0);
            String host=serviceInstance.getHost();
            int port=serviceInstance.getPort();
            String url = "http://"+host+":"+port+"/user/" + id;
            System.out.println("useservice url is :"+url);
            return this.restTemplate.getForObject(url, User.class);
        }*//*
        //返回空对象
        //return new User();

        //第二种方式
        //通过负载均衡算法获得服务节点id  默认是轮询算法
      *//*  ServiceInstance serviceInstance = client.choose("user-service");
        String host=serviceInstance.getHost();
        int port=serviceInstance.getPort();
        String url = "http://"+host+":"+port+"/user/" + id;
        System.out.println("useservice url is :"+url);*//*


        //第三种方式  在restTemplate  的bean配置处加上注解配置 @LoadBalanced  内置了拦截去拦截restTemplate请求  拦截器LoadBalancerInterceptor
        //user-service  服务id
        String url="http://user-service/user/"+id;
        return this.restTemplate.getForObject(url, User.class);
    }*/

    /**
     * 开启  线程隔离  服务降级
     * @param id
     * @return
     */
    //@HystrixCommand(fallbackMethod ="queryUserByIdFallback" ) //单个方法自定义写法

    /**
     * HystrixCommandProperties   默认的一些配置属性
     * 测试熔断器的熔断机制
     * @param id
     * @return
     */
/*    @HystrixCommand(commandProperties = {
           // @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")//定义服务超时时长
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//熔断器请求量的阈值
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//熔断器休眠时间窗   10s
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//熔断器错误百分比
    })
    //与类上的注解DefaultProperties  结合使用
    public String queryUserById(Long id){
        //user-service  服务id
        String url="http://user-service/user/"+id;
        return this.restTemplate.getForObject(url, String.class);
    }*/


    @Autowired
    private UserClient userClient;

    @HystrixCommand
    public String queryUserById(Long id){
        //user-service  服务id
      /*  String url="http://user-service/user/"+id;
        return this.restTemplate.getForObject(url, String.class);*/
        User user = userClient.queryById(id);
        return JSONObject.toJSONString(user);
    }

    /**
     * 失败回调方法入参和返回值必须与原方法一致
     *
     * @param id
     * @return
     */
    public String queryUserByIdFallback(Long id){
        return "服务器繁忙，请稍等。。。";
    }

    /**
     * 统一的降级处理方法不需要入参参数了
     * @return
     */
    public String Fallback(){
        return "服务器繁忙，请稍等。。。11";
    }
}