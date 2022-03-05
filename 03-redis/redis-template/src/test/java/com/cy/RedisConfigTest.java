package com.cy;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisConfigTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void testBlogOper01(){
        ValueOperations vo = redisTemplate.opsForValue();
        Blog blog = new Blog();
        blog.setId(100l);
//        blog.setTitle("hello redis");
        vo.set("blog-redis", blog);
    }

    @Test
    void testBlogOper02(){
        HashOperations ho = redisTemplate.opsForHash();

        ho.put("blog-redis02", "id","200l");
        ho.put("blog-redis02","title","kobe hello redis");
    }


}
