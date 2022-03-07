package com.cy;

import com.cy.pojo.Menu;
import com.cy.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuServiceTest {
    @Autowired
    @Qualifier("aopCacheMenuServiceImpl")
    private MenuService menuService;


    @Test
    void testSelectById(){
        Menu menu = menuService.selectById(8L);
        System.out.println(menu);
    }


    @Test
    void testInsertMenu(){
        Menu menu = new Menu();
        menu.setName("kobekjamesjames resource");
        menu.setPermission("sys:res:daochu");
        menuService.insertMenu(menu);

    }


    @Test
    void testUpdateMenu(){
        Menu menu = menuService.selectById(8L);
        menu.setName("import resource kobe3456789kobe");
        menu.setPermission("sys:res:import");

        menuService.updateMenu(menu);
    }

}
