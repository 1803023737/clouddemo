package com.cloud.consumer.client;

import com.cloud.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * fallback  实例还需要纳入spring容器中  所以需要打上component注解
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public User queryById(Long id) {
        User user=new User();
        user.setName("未知用户");
        return user;
    }
}
