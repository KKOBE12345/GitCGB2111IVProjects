package com.cy;

import com.google.gson.Gson;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static sun.plugin2.util.PojoUtil.toJson;

public class JedisTest {

    @Test  //Jedis连接池
    public void testJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数100
        config.setMaxTotal(100);
        //设置最大空闲连接数
        config.setMaxIdle(16);
        //创建连接池并且将设置的配置也加入  默认最大是八个连接数  现在是100
        JedisPool jedisPool = new JedisPool(config,"192.168.126.128", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("kobe","123");
        String kobe = resource.get("kobe");
        System.out.println(kobe);
        resource.close();
        jedisPool.close();


    }

    //课堂练习
    //基于hash类型将将02中的对象写到REDIS
    //并且尝试进行  查询 修改  删除等操作
    @Test
    public void testHash01(){
        Jedis jedis=new Jedis("192.168.126.128", 6379);
        String key = UUID.randomUUID().toString();
        jedis.hset(key,"id","500");
        jedis.hset(key,"name","kobe");

        jedis.hset(key,"name","jordan");
        Map<String, String> map = jedis.hgetAll(key);
        System.out.println(map);
        //jedis.del(key);
        jedis.expire(key,123);
        jedis.close();

    }

    //测试字符串操作

    @Test
    public void testStringOper01(){
        Jedis jedis=new Jedis("192.168.126.128", 6379);
        //新增数据
        jedis.set("id", "200");
        jedis.set("name", "kobe");
        //修改数据
        jedis.incr("id");
        jedis.incr("id");
        jedis.incrBy("id",100);
        jedis.set("name", "mike");
        //查询数据
        String id = jedis.get("id");
        String name = jedis.get("name");
        System.out.println("id="+id+";name="+name);
        //删除数据
        jedis.del("name");

        jedis.close();


    }

    @Test
    public void testStringOper02(){
        Jedis jedis=new Jedis("192.168.126.128", 6379);
        //数据操作(将yigemap对象转化成一个json对象  然后写进REDIS)
        Map<String,String> map=new HashMap<>();
        map.put("id", "100");
        map.put("name", "kobe");
        Gson gson=new Gson();
        String jsonStr=gson.toJson(map);
        String key = UUID.randomUUID().toString();
        //将字符串写入到JSON
        jedis.set(key,jsonStr);


        jedis.close();



    }

    @Test
    public void TestConnection(){
        Jedis jedis=new Jedis("192.168.126.128", 6379);
        String res = jedis.ping();
        System.out.println(res);
        jedis.close();
    }
}
