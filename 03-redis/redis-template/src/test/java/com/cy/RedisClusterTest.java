package com.cy;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;



@SpringBootTest
public class RedisClusterTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testMasterReadWrite(){
        //1.获取数据操作对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //2.读写数据
        valueOperations.set("city","beijing");
        Object city=valueOperations.get("city");
        System.out.println(city);
    }

}
