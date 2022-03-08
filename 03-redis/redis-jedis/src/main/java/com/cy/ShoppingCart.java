package com.cy;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class ShoppingCart {

    public static void addCart(Long userId,Long productId,int num){
        Jedis jedis = new Jedis("192.168.126.128",6379);
//        jedis.auth("123456");
        //redis没有设置密码  上面这行可以不需要
        jedis.hincrBy("cart:"+userId, String.valueOf(productId),num);
        jedis.close();
    }

    public static Map<String,String> listCart(Long userId){
        Jedis jedis = new Jedis("192.168.126.128",6379);
        Map<String, String> map = jedis.hgetAll("cart:" + userId);
//        System.out.println(map);
        return map;

    }

    public static void main(String[] args) {
        addCart(101L, 24L, 21);
        addCart(101L, 22L, 60);
        addCart(101L, 255L, 81);
        Map<String, String> cart = listCart(101L);
        System.out.println(cart);
    }

}
