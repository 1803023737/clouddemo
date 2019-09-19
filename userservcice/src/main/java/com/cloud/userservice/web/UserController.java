package com.cloud.userservice.web;

import com.cloud.userservice.pojo.User;
import com.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试  hystrix熔断的机制
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public User queryById(@PathVariable("id")Long id){
//        try {
            //模拟请求超时  测试hystrix
            if (id%2==0){
                //当偶数的时候  超时   为了手动测试熔断器
                throw new RuntimeException("接口异常");
//                Thread.sleep(3000);
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return userService.queryByID(id);
    }


  /*  @RequestMapping("/{id}")
    public User queryById(@PathVariable("id")Long id){
        return userService.queryByID(id);
    }*/


}
