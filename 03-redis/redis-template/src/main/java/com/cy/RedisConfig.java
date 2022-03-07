package com.cy;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * 在配置类自定义redistemplate的配置
 * */

@Configuration
public class RedisConfig {
    //这是一个简单定制方案
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, Object> template = new RedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        //修改默认的序列化方式（默认是JDK序列化方式）
//        template.setKeySerializer(RedisSerializer.string());  //大key
//        template.setHashKeySerializer(RedisSerializer.string());  //小key
//        //设置值的序列化方式
//        template.setValueSerializer(RedisSerializer.json());
//        template.setHashValueSerializer(RedisSerializer.json());
//        return template;
//    }

    //下面是一个高级定制   改变默认Json方式

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //修改默认的序列化方式（默认是JDK序列化方式）
        template.setKeySerializer(RedisSerializer.string());  //大key
        template.setHashKeySerializer(RedisSerializer.string());  //小key
        //设置值的序列化方式
        template.setValueSerializer(jsonSerializer());
        template.setHashValueSerializer(jsonSerializer());
        //官方建议  只要改了默认配置   要执行下下面的语句  更新下属性配置
        template.afterPropertiesSet();
        return template;
    }

    //配置json序列化
    private RedisSerializer<Object> jsonSerializer() {
        //1.定义JSON序列化  反序列化对象
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer=
                new Jackson2JsonRedisSerializer<Object>(Object.class);
        //2.定义序列化规则  序列化规则由objectmapper来定义
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.GETTER, //类中的get方法
                JsonAutoDetect.Visibility.ANY);    //这个表示任意的访问修饰符
        //假如属性值为null  则不再进行此属性的序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //将对象类型写入到序列化的字符串中
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), //多态校验
                ObjectMapper.DefaultTyping.NON_FINAL,   //非FINAL类型允许序列化
                JsonTypeInfo.As.PROPERTY);      //类型以属性形式进行存储
        jsonRedisSerializer.setObjectMapper(objectMapper);

        return jsonRedisSerializer;
    }
}
