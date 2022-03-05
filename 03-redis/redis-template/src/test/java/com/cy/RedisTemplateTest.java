package com.cy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;  //JUNIT  5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class RedisTemplateTest {


    @Autowired
    private RedisTemplate redisTemplate;
    // RedisTemplate实现数据操作时  对象会默认进行序列化  序列化方式默认是JDK自带的序列化机制

    @Test  //设计一个blog对象 然后通过redisTemplate 对象 将此类对象写进redis数据库
    void testHashOper02() throws JsonProcessingException {
        ValueOperations vo = redisTemplate.opsForValue();
        //存储Blog对象
        Blog blog=new Blog();
        blog.setId(100L);
        blog.setTitle("redis123");
        vo.set("blog-kobe", blog);  //序列化
        Object o = vo.get("blog-kobe");   //反序列化
        System.out.println(o);

        //基于HASHoperations  存取blog对象
        HashOperations ho = redisTemplate.opsForHash();
        ObjectMapper objectMapper=new ObjectMapper();   //jackson中的API  需要掌握啊啊啊啊啊啊啊啊啊啊
        String jsonStr = objectMapper.writeValueAsString(blog);
        Map map = objectMapper.readValue(jsonStr, Map.class);
        System.out.println(map);
        ho.putAll("blog-kobe02", map);
        ho.put("blog-kobe02", "id", 200);
        map= ho.entries("blog-kobe02");
        System.out.println(map);

    }


    @Test
    void testHashOper01(){
        //获取 HASH 操作对象
        HashOperations ho = redisTemplate.opsForHash();
        //存储数据
        ho.put("user101", "sex", "1");
        ho.put("user101", "title", "pretty");
        //获取数据
        Object id = ho.get("user101", "id");
        Object title = ho.get("user101", "title");
        System.out.println(id+"哈哈"+title);
        Map user101 = ho.entries("user101");
        System.out.println(user101);


    }

    @Test
    void testStringOper02(){
        ValueOperations vo = redisTemplate.opsForValue();
        //存储数据
        String token = UUID.randomUUID().toString();
        vo.set(token,"admin");
        //指定序列化方式  进行数据存储
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        vo.set(token,"kobe");
        //更新数据
        vo.set(token,"Jack");
        //删除数据
        //不好意思  没有移除的方法   需要在存数据的时候指定数据的有效期
        //生产环境必须设置
        vo.set("player","kobekobe",Duration.ofSeconds(24));


    }

    @Test
    void testStringOpr(){
        //获取字符串操作对象VO
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //操作redis数据
        valueOperations.set("x", "100");
        Object x = valueOperations.get("x");
        //  valueOperations.increment("x");  //已经存在的数据 不可以这样操作
        System.out.println(x);
        Long y = valueOperations.increment("y");
        y = valueOperations.increment("y");
        //Object y = valueOperations.get("y");  //这边会反序列失败
        System.out.println(y);
        //设置key的有效时长
        valueOperations.set("z", 100, Duration.ofSeconds(20));

    }

    @Test
    void testconnection(){
        RedisConnection connection = redisTemplate.
                getConnectionFactory().getConnection();
        String res = connection.ping();
        System.out.println(res);
    }
}
