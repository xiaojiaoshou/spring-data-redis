package com.example.redistemplate.config;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @类描述: 自定义的redistemplate 区分事务与非事务
 * @author guohuixiang
 * @date 2019/8/9
 */
public class RedisTemplateDelegate<K, V> extends RedisTemplate<K, V> {
//    //@Resource(name = "notSupportTransactionRedisTemplate")
//    private RedisTemplate<K, V> notSupportTransactionRedisTemplate;
//
//    @Override
//    public <T> T execute(RedisCallback<T> action, boolean exposeConnection, boolean pipeline) {
//        //判断是否有@Transactional注解，如果有就用支持事务的RedisTemplate
//        if (TransactionSynchronizationManager.isActualTransactionActive()) {
//            return super.execute(action, exposeConnection, pipeline);
//        } else {
//            return notSupportTransactionRedisTemplate.execute(action, exposeConnection, pipeline);
//        }
//    }
//
//    @Override
//    public <T> T execute(SessionCallback<T> session) {
//        if (TransactionSynchronizationManager.isActualTransactionActive()) {
//            return super.execute(session);
//        } else {
//            return notSupportTransactionRedisTemplate.execute(session);
//        }
//    }
//
//    @Override
//    public List<Object> executePipelined(final SessionCallback<?> session, final RedisSerializer<?> resultSerializer) {
//        if (TransactionSynchronizationManager.isActualTransactionActive()) {
//            return super.executePipelined(session, resultSerializer);
//        } else {
//            return notSupportTransactionRedisTemplate.executePipelined(session, resultSerializer);
//        }
//    }
}