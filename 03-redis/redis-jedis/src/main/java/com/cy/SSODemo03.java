package com.cy;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class SSODemo03 {
    /**
     * 执行投票逻辑
     * @param activityId 活动id
     * @param userId 用户id
     * @return 是否投票成功
     */
    static boolean doVote(String activityId,String userId){
        //1.参数校验
        //2.执行投票逻辑
        //2.1连接redis
        Jedis jedis=JedisDataSource.getJedisConnection();
        //2.2检查是否已存于过投票，基于检查结果执行投票或取消投票
        Boolean flag = jedis.sismember(activityId, userId);//set类型
        if(flag){//true表示已经参与过投票，此时可以取消投票
            jedis.srem(activityId, userId);
            jedis.close();
            return false;
        }
        //假如没有参与过投票，则直接投票
        jedis.sadd(activityId,userId);
        jedis.close();
        return true;
    }

    /**
     * 基于活动id统计投票总数
     * @param activityId
     * @return
     */
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

    /**
     * 获取投票的用户
     * @param activityId
     * @return
     */
    static Set<String> doGetUsers(String activityId){
        //1.参数校验
        //2.获取投票用户
        Jedis jedis=JedisDataSource.getJedisConnection();
        Set<String> users = jedis.smembers(activityId);
        jedis.close();
        return users;
    }
    public static void main(String[] args) {
        //1.定义活动
        String activityId="201";
        String user1="1001";
        long count=doCount(activityId);

        String user2="1002";
        String user3="1003";
        //2.基于活动id进行投票
        boolean flag=doVote(activityId,user1);
        flag=doVote(activityId,user2);
        flag=doVote(activityId,user3);
        //3.获取总票数
        count=doCount(activityId);
        //4.查看哪些人参与了投票
        Set<String> users = doGetUsers(activityId);
        System.out.println(count);
        System.out.println(users);
    }
}
