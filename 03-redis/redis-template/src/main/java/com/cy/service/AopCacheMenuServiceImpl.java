package com.cy.service;

import com.cy.dao.MenuMapper;
import com.cy.pojo.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopCacheMenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Cacheable(value = "menuCache",key = "#id")  //这是一个缓存切入点方法
    @Override
    public Menu selectById(Long id) {
        log.info("DATA FROM RDBMS");
        return menuMapper.selectById(id);
    }

    @Override
    @CachePut(value = "menuCache",key = "#menu.id")
    public Menu insertMenu(Menu menu) {
        log.info("insert before：{}", menu);
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    @CachePut(value = "menuCache",key = "#menu.id")
    public Menu updateMenu(Menu menu) {
        menuMapper.updateById(menu);
        return menu;
    }
}
