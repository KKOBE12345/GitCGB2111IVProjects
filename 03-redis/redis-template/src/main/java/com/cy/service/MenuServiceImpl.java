package com.cy.service;

import com.cy.dao.MenuMapper;
import com.cy.pojo.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger log=
            LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;


//    @Autowired
//    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate" )  //这种写法  参考官方
    private ValueOperations vo;

    public Menu selectById(Long id) {
        //先查REDIS 假如有 从缓存中取数据
        //
        //ValueOperations vo = redisTemplate.opsForValue();
        String key = String.valueOf(id);
        Object o = vo.get(key);
        if(o!=null){
            log.info("data from redis");
            return (Menu) o;

        }

        Menu menu = menuMapper.selectById(id);
        log.info("data from rdbms");
        //将数据存储到REDIS数据库中
        vo.set(key,menu, Duration.ofSeconds(180));
        return menu;
    }

    public Menu insertMenu(Menu menu) {
        log.info("before insert the menu:{}", menu);
        menuMapper.insert(menu);
        log.info("after insert the menu:{}", menu);

        //ValueOperations vo = redisTemplate.opsForValue();
        vo.set(String.valueOf(menu.getId()), menu);

        return menu;   //插入之后会多一个ID
    }


    public Menu updateMenu(Menu menu) {
        //更新MYSQL
        menuMapper.updateById(menu);
        //修改完之后 更新REDIS  保证缓存与实际的内容一致
        //ValueOperations vo = redisTemplate.opsForValue();
        vo.set(String.valueOf(menu.getId()), menu);

        return menu;
    }
}
