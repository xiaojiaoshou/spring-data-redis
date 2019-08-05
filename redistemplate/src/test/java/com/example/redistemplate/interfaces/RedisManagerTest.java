package com.example.redistemplate.interfaces;

import com.example.redistemplate.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisManagerTest {
    @Autowired
    private RedisManager redisManager;

    @Test
    public void expire() {
    }

    @Test
    public void getExpire() {
    }

    @Test
    public void hasKey() {
    }

    @Test
    public void del() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
        boolean isSuccess = redisManager.set("key", "hello");
        if (isSuccess) {
            System.out.println("存入成功!");
        } else {
            System.out.println("存入失败!");
        }
    }

    @Test
    public void set1() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void decr() {
    }

    @Test
    public void hget() {
        Object object = redisManager.hget("aaa", "ccc");
        User user = (User) object;
        System.out.println(user);

    }

    @Test
    public void hmget() {
    }

    @Test
    public void hmset() {

    }

    @Test
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

    @Test
    public void hset() {
        String key = "bbb";
        String item = "ddd";
        User user = User.builder().userName("张三").age(23).sex("男").build();
        boolean isSuccess = redisManager.hset(key, item, user,500);
        if (isSuccess) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }
    }

    @Test
    public void hset1() {
    }

    @Test
    public void hdel() {
    }

    @Test
    public void hHasKey() {
    }

    @Test
    public void hincr() {
    }

    @Test
    public void hdecr() {
    }

    @Test
    public void sGet() {
    }

    @Test
    public void sHasKey() {
    }

    @Test
    public void sSet() {
    }

    @Test
    public void sSetAndTime() {
    }

    @Test
    public void sGetSetSize() {
    }

    @Test
    public void setRemove() {
    }

    @Test
    public void lGet() {
    }

    @Test
    public void lGetListSize() {
    }

    @Test
    public void lGetIndex() {
    }

    @Test
    public void lSet() {
    }

    @Test
    public void lSet1() {
    }

    @Test
    public void lSet2() {
    }

    @Test
    public void lSet3() {
    }

    @Test
    public void lUpdateIndex() {
    }

    @Test
    public void lRemove() {
    }
}