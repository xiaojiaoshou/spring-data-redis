package com.example.redistemplate.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

//@EnableTransactionManagement
@Configuration
public class RedisConfig {

    @Autowired
    JedisConfig jedisConfig;
    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplateDelegate<>();
        jedisConnectionFactory(template,true);
        return template;
    }

    @Bean(value ="notSupportTransactionRedisTemplate" )
    public RedisTemplate<String, Object> notSupportTransactionRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplateDelegate<>();
        jedisConnectionFactory(template,false);
        return template;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        //rf.setDatabase(jedisConfig.database);
        rf.setHostName(jedisConfig.host);
        rf.setPort(jedisConfig.port);
        int to = Integer.parseInt(jedisConfig.timeout.substring(0, jedisConfig.timeout.length() - 2));
        //JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        //jedisClientConfiguration.connectTimeout(Duration.ofMillis(to));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisConfig.maxIdle);
        jedisPoolConfig.setMinIdle(jedisConfig.minIdle);
        jedisPoolConfig.setMaxTotal(jedisConfig.maxActive);
        int l = Integer.parseInt(jedisConfig.maxWait.substring(0, jedisConfig.maxWait.length() - 2));
        jedisPoolConfig.setMaxWaitMillis(l);
        jpb.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
        return jedisConnectionFactory;
    }



    private RedisTemplate<String, Object> jedisConnectionFactory(RedisTemplate<String, Object> template, @NotNull Boolean  enableTransactionSupport) {
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
}
