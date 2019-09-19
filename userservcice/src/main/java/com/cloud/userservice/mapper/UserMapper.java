package com.cloud.userservice.mapper;

import com.cloud.userservice.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 继承通用mapper
 */
@Repository
public interface UserMapper extends Mapper<User> {

}
