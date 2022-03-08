package com.cy;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisTransactionTests {
    @Test
    public void test(){
        Jedis jedis = JedisDataSource.getJedisConnection();
        jedis.set("kobe", "500");
        jedis.set("james","100");
        Transaction multi = jedis.multi();
        try{
            multi.decrBy("kobe", 100);
            multi.incrBy("james", 100);
            int a=100;
            int b=0;
            a=a/b;
            multi.exec();
        }catch (Exception e){
            e.printStackTrace();
            multi.discard();
        }finally {
            List<String> mget = jedis.mget("kobe", "james");
            System.out.println(mget);
            jedis.close();
        }


    }
}
