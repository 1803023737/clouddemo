package com.cloud.userservice.service;

import com.cloud.userservice.mapper.UserMapper;
import com.cloud.userservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryByID(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

}
