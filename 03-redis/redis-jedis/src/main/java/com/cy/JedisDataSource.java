package com.cy;

import org.omg.CORBA.PUBLIC_MEMBER;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 创建数据源对象
 * 此对象会对外提供一个JEDIS连接池对象jedispool
 * 且只能是一个
 * 请问你该怎么设计这个对象
 * */

public class JedisDataSource {
//    private static JedisPool jedisPool;
//    //方案一  饿汉式  类加载时创建池对象
//    static{
//        //构建连接池的配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        //设置最大连接数100
//        config.setMaxTotal(100);
//        //设置最大空闲连接数
//        config.setMaxIdle(16);
//        //创建连接池并且将设置的配置也加入  默认最大是八个连接数  现在是100
//        JedisPool jedisPool = new JedisPool(config,"192.168.126.128", 6379);
//    }
//    public static JedisPool getJedisPool(){
//        return jedisPool;
//    }


//方案2 懒加载  何时用 什么时候创建   只能创建一次
    private static volatile JedisPool jedisPool;
    public static Jedis getJedisConnection(){
        if(jedisPool==null){
            synchronized (JedisDataSource.class){
                if(jedisPool==null){
                    JedisPoolConfig config = new JedisPoolConfig();
                    //设置最大连接数100
                    config.setMaxTotal(100);
                    //设置最大空闲连接数
                    config.setMaxIdle(16);
                    //创建连接池并且将设置的配置也加入  默认最大是八个连接数  现在是100
                    jedisPool = new JedisPool(config,"192.168.126.128", 6379);
                }

            }

        }

        return jedisPool.getResource();
    }

    public static void close(){
        jedisPool.close();
    }
}
