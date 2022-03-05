package com.cy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

@SpringBootTest
public class StringRedisTemplateTest {


    //stringRedisTemplate  默认序列化方式改为了String
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    void testStringOper01(){
        ValueOperations<String, String> vo = stringRedisTemplate.opsForValue();
        vo.set("color", "red");
        String color = vo.get("color");
        System.out.println(color);
    }

    @Test
    void testStringOper02(){
        HashOperations<String, Object, Object> ho = stringRedisTemplate.opsForHash();
        ho.put("blog","id","1001");
        ho.put("blog","title","robottttt");
        Map<Object, Object> map = ho.entries("blog");
        System.out.println(map);
    }

    @Test
    void testStringOper03() throws JsonProcessingException {
        ValueOperations<String, String> vo = stringRedisTemplate.opsForValue();
        Blog blog = new Blog();
        blog.setId(100l);
        blog.setTitle("曼巴精神");
        vo.set("blog-kobekobe", new ObjectMapper().writeValueAsString(blog));
        String s = vo.get("blog-kobekobe");
        System.out.println(s);

    }

}
