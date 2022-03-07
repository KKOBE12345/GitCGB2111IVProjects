package com.cy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class CacheManagerConfig {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheConfiguration cacheConfig=
                RedisCacheConfiguration
                        .defaultCacheConfig()  //制定了key和值的序列化方式  也可以只设置的值的序列化方式
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        return RedisCacheManager.builder(connectionFactory).
                cacheDefaults(cacheConfig).
                build();
    };
}
