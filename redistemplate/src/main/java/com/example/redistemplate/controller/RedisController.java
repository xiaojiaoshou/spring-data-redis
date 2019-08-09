package com.example.redistemplate.controller;

import com.example.redistemplate.entity.Response;
import com.example.redistemplate.entity.User;
import com.example.redistemplate.interfaces.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @类描述: 测试controller
 * @author guohuixiang
 * @date 2019/8/8
 */
@RestController
public class RedisController {
    @Autowired
    private RedisManager redisManager;


    public void expire() {
    }


    public void getExpire() {
    }


    public void hasKey() {
    }


    public void del() {
    }

    @RequestMapping("/get")
    public Response get(String key) {
        Object o = redisManager.get(key);

        return Response.setSuccess(o);
    }

    @RequestMapping("/set")
    public Response set(String key, String value) {
        boolean isSuccess = redisManager.set(key, value);
        if (isSuccess) {
            System.out.println("存入成功!");
            return Response.setSuccess();
        } else {
            System.out.println("存入失败!");
            return Response.setFaile("保存失败!");
        }
    }


    public void set1() {
    }


    public void incr() {
    }


    public void decr() {
    }


    public void hget() {
        Object object = redisManager.hget("aaa", "ccc");
        User user = (User) object;
        System.out.println(user);

    }


    public Response hmget(String key) {
        Map<Object, Object> hmget = redisManager.hmget(key);
        return Response.setSuccess(hmget);
    }


    public void hmset() {
    }


    public void hmset1() {
        String key = "bbb";
        String item = "eee";
        User user = User.builder().userName("李四").age(26).sex("男").build();
        boolean isSuccess = redisManager.hset(key, item, user);
        if (isSuccess) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }
    }

    @RequestMapping("/hset")
    public Response hset() {
        String key = "测试key";
        String item = "测试feild";
        User user = User.builder().userName("张三").age(23).sex("男").build();
        boolean isSuccess = redisManager.hset(key, item, user, 500);
        if (isSuccess) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }
        return Response.setSuccess(isSuccess);
    }


    public void hset1() {
    }


    public void hdel() {
    }


    public void hHasKey() {
    }


    public void hincr() {
    }

    public void hdecr() {
    }


    public void sGet() {
    }


    public void sHasKey() {
    }

    public void sSet() {
    }


    public void sSetAndTime() {
    }


    public void sGetSetSize() {
    }


    public void setRemove() {
    }


    public void lGet() {
    }


    public void lGetListSize() {
    }


    public void lGetIndex() {
    }


    public void lSet() {
    }


    public void lSet1() {
    }


    public void lSet2() {
    }


    public void lSet3() {
    }


    public void lUpdateIndex() {
    }


    public void lRemove() {
    }
}
