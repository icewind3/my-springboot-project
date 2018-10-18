package com.icewind.oauth2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icewind
 * @date 2018/1/17
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private int redirects;

    /**
     * 连接池设置
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(getClusterConfiguration());
    }

    /**
     * Redis Cluster参数配置
     *
     * @return
     */
    private RedisClusterConfiguration getClusterConfiguration() {
//        Map<String, Object> source = new HashMap<>();
//        source.put("spring.redis.cluster.nodes", clusterNodes);
//        source.put("spring.redis.cluster.max-redirects", redirects);
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(StringUtils.commaDelimitedListToSet(clusterNodes));
        redisClusterConfiguration.setPassword(RedisPassword.of(password));
        redisClusterConfiguration.setMaxRedirects(redirects);
        return redisClusterConfiguration;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 开启事务支持
        redisTemplate.setEnableTransactionSupport(true);
        // 使用String格式序列化缓存键
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(1000L));
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(getRedisCacheConfigurationMap(defaultCacheConfig))
                .build();
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append(".");
            sb.append(method.getName()).append("[");
            for (Object obj : objects) {
                if (obj == null) {
                    sb.append("null");
                } else {
                    sb.append(obj.toString());
                }
                sb.append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
            return sb.toString();
        };
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap(RedisCacheConfiguration defaultCacheConfig) {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(4);
        configMap.put("user", defaultCacheConfig.entryTtl(Duration.ofSeconds(1000L)));
        configMap.put("username", defaultCacheConfig.entryTtl(Duration.ofSeconds(2000L)));
        configMap.put("countCache", defaultCacheConfig.entryTtl(Duration.ofSeconds(20L)));
        return configMap;
    }

//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(port);
//        jedisConnectionFactory.setPassword(password);
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
}
