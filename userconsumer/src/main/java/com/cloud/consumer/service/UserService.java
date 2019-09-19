package com.cloud.consumer.service;

import com.cloud.consumer.dao.UserDao;
import com.cloud.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<String> querUserByIds(List<Long> ids){
        List<String> users = new ArrayList<>();
        for (Long id : ids) {
            String user = this.userDao.queryUserById(id);
            users.add(user);
        }
        return users;
    }
}