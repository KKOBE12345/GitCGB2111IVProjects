package com.cy;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class MasterSlaveTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testMasterReadWrite(){//配置文件端口为6379
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("role", "master6379");
        Object role = valueOperations.get("role");
        System.out.println(role);
    }

    @Test
    void testSlaveRead(){//配置文件端口为6380
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object role = valueOperations.get("role");
        System.out.println(role);
    }

}
