package com.cy;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**用户登录成功后  将状态信息存储到redis
 * 1.完成用户身份认证
 * 2.完成用户资源访问的授权
 * 3.返回用户一个token
 * 4.用户登录成功后 可以携带token访问资源
 * 资源服务器验证token的有效性
 * 判定用户是否有访问的权限
 * */
public class SSODemo01 {
    //登录
    public static  String doLogin(String username,String password){
        if(username==null||username==""){
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(!"kobe".equals(username))   {
            throw new IllegalArgumentException("用户不存在");
        }
        if(!"123456".equals(password)){
            throw new IllegalArgumentException("密码不正确");
        }
        Jedis jedis = JedisDataSource.getJedisConnection();
        String token = UUID.randomUUID().toString();
        jedis.hset(token,username,"kobe");
        jedis.hset(token,"permissions","sys:res:view,sys:res:update");
        jedis.expire(token,3);
        jedis.close();


        return token;
    }
    //执行资源访问逻辑
    public static Object doGetResource(String token){
        //校验token的值
        if(token==null||token==""){
            throw new IllegalArgumentException("用户名不能为空");
        }

        //基于token查询用户的值
        Jedis jedis = JedisDataSource.getJedisConnection();
        Map<String, String> userMap = jedis.hgetAll(token);
        if(userMap==null||userMap.size()==0){
            throw new IllegalArgumentException("用户登陆超时 请重新登录");
        }
        String permissions = userMap.get("permissions");
        String[] permissionArray = permissions.split(",");
        List<String> permissionList = Arrays.asList(permissionArray);
        if(!permissionList.contains("sys:res:view")){
            throw new RuntimeException("对不起 你无权访问这个资源");
        }


        //判定用户是否有资源的访问权限

        return "your one piece";
    }
    public static void main(String[] args) throws InterruptedException {
        //1.执行登录操作
        String username="kobe";
        String password="123456";
        String token=doLogin(username,password);
        System.out.println(token);

        //2.携带令牌 取访问资源
        Thread.sleep(5000);
        Object resource=doGetResource(token);
        System.out.println(resource);
    }
}
