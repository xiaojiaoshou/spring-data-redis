package com.example.redistemplate.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.validation.constraints.NotNull;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplateDelegate<>();
        //  是否支持事务
        jedisConnectionFactory(template, jedisConnectionFactory, false);
        return template;
    }


    private RedisTemplate<String, Object> jedisConnectionFactory(RedisTemplate<String, Object> template,
                                                                 JedisConnectionFactory jedisConnectionFactory,
                                                                 @NotNull Boolean enableTransactionSupport) {
        // 1.创建 redisTemplate 模版
        // RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(jedisConnectionFactory);
        // 3.创建 序列化类
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 4.设置可见度
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 5.启动默认的类型
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 6.序列化类，对象映射设置
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 7.设置 value 的转化格式和 key 的转化格式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //8.設置散列的序列化器
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        template.setEnableTransactionSupport(enableTransactionSupport);
        return template;
    }

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
//        //rf.setDatabase(jedisConfig.database);
//        rf.setHostName(jedisConfig.host);
//        rf.setPort(jedisConfig.port);
//        int to = Integer.parseInt(jedisConfig.timeout.substring(0, jedisConfig.timeout.length() - 2));
//        //JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//        //jedisClientConfiguration.connectTimeout(Duration.ofMillis(to));
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(jedisConfig.maxIdle);
//        jedisPoolConfig.setMinIdle(jedisConfig.minIdle);
//        jedisPoolConfig.setMaxTotal(jedisConfig.maxActive);
//        int l = Integer.parseInt(jedisConfig.maxWait.substring(0, jedisConfig.maxWait.length() - 2));
//        jedisPoolConfig.setMaxWaitMillis(l);
//        jpb.poolConfig(jedisPoolConfig);
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
//        return jedisConnectionFactory;
//    }

}
