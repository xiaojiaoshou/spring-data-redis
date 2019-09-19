package com.example.redistemplate.interfaces;

import com.example.redistemplate.entity.User;

/**
 * @类描述:
 * @author guohuixiang
 * @date 2019/8/21
 */
public interface RedisService {

     User getUser(String userName);
}
