package com.example.redistemplate.service;

import com.example.redistemplate.entity.User;
import com.example.redistemplate.interfaces.RedisService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @类描述:
 * @author guohuixiang
 * @date 2019/8/21
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Cacheable(value = "db0", key = "#userName")
    public User getUser(String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setAge(22);
        user.setSex("女");
        return user;
    }
}
