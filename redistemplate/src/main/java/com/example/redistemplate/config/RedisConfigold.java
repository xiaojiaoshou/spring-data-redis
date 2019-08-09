package com.example.redistemplate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfigold {

    /**
     * 1.创建JedisPoolConfig对象。在该对象中完成一些链接池配置
     *
     * @ConfigurationProperties:会将前缀相同的内容创建一个实体。
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
		/*//最大空闲数
		config.setMaxIdle(10);
		//最小空闲数
		config.setMinIdle(5);
		//最大链接数
		config.setMaxTotal(20);*/
        System.out.println("默认值：" + config.getMaxIdle());
        System.out.println("默认值：" + config.getMinIdle());
        System.out.println("默认值：" + config.getMaxTotal());
        return config;
    }

    /**
     * 2.创建JedisConnectionFactory：配置redis链接信息
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config) {
        System.out.println("配置完毕：" + config.getMaxIdle());
        System.out.println("配置完毕：" + config.getMinIdle());
        System.out.println("配置完毕：" + config.getMaxTotal());

        JedisConnectionFactory factory = new JedisConnectionFactory();

        //关联链接池的配置对象
        factory.setPoolConfig(config);
        //配置链接Redis的信息
        //主机地址
		factory.setHostName("39.105.215.231");
		//端口
		//factory.setPort(6379);
        return factory;
    }

//    /**
//     * 3.创建RedisTemplate:用于执行Redis操作的方法
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        // 1.创建 redisTemplate 模版
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        // 2.关联 redisConnectionFactory
//        template.setConnectionFactory(jedisConnectionFactory);
//        // 3.创建 序列化类
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        // 4.设置可见度
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 5.启动默认的类型
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        // 6.序列化类，对象映射设置
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 7.设置 value 的转化格式和 key 的转化格式
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        //8.設置散列的序列化器
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
}
