package com.cy;


import redis.clients.jedis.Jedis;

import java.util.Set;

//基于某个活动的id的简易投票系统设计与实现
//同一个人不能重复投票   第二次投票  则视为取消票数
//获取投票人数
public class SSODemo02 {
    /**执行投票逻辑*/
    static boolean dovote(String activityId,String userId){
        //1.参数校验
        //2.执行投票逻辑
        //3.投票结果存到REDIS中
        //检查redis连接   检查是否已经投票
        Jedis jedis = JedisDataSource.getJedisConnection();
        Boolean flag = jedis.sismember(activityId, userId);//set类型
        if(flag){  //这样表示已经参与过投票
            jedis.srem(activityId, userId);
            jedis.close();
            return false;
        }
        //加入没有参与过投票   则直接投票
        jedis.sadd(activityId, userId);
        jedis.close();

        return true;
    }
    /**下面注释的这个方法 因为方法参数多加了一个引号
     * 害得我排错排了好久  操操操！！！！*/
    //获取投票总数的方法
//    static Long doCount(String activityId){
//        //参数校验
//        //2.1连接redis
//        //2.2 获取总数
//        //2.3 释放资源  返回投票总数
//        Jedis jedis = JedisDataSource.getJedisConnection();
//        Long count = jedis.scard("activityId");
//        jedis.close();
//
//
//        return count;
//    }

    static Long doCount(String activityId){
        //1.参数校验
        //2.获取投票总数
        //2.1连接redis
        Jedis jedis=JedisDataSource.getJedisConnection();
        //2.2获取总数
        Long count = jedis.scard(activityId);
        //2.3释放资源
        jedis.close();
        //3.返回投票总数
        return count;
    }

    //统计是哪些人参与了投票
    static Set<String> doGetUser(String activityId){
        Jedis jedis = JedisDataSource.getJedisConnection();
        Set<String> users = jedis.smembers(activityId);
        jedis.close();
        return users;
    }
    public static void main(String[] args) {
        //1.定义活动
        String activityId="301";
        String user1="1001";
        String user2="1002";
        String user3="1003";
        String user4="1004";
        String user5="1005";
        //2.基于活动id  进行投票
        dovote(activityId,user1);  //true
        dovote(activityId,user2);
        Long count = doCount(activityId);
        System.out.println(count);
        dovote(activityId,user3);
        dovote(activityId,user4);
        dovote(activityId,user5);
        //3.获取总票数
        count = doCount(activityId);
        Set<String> users = doGetUser(activityId);
        System.out.println(count);
        System.out.println(users);
        //4.查看哪些人进行了投票
    }
}
