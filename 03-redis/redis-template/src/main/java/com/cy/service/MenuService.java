package com.cy.service;

import com.cy.pojo.Menu;

public interface MenuService {

    //基于ID查找菜单
    Menu selectById(Long id);
    //插入菜单数据
    Menu insertMenu(Menu menu);
    //修改菜单数据
    Menu updateMenu(Menu menu);
}
