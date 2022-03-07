package com.cy.controller;


import com.cy.pojo.Menu;
import com.cy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    @Qualifier("menuServiceImpl")
    private MenuService menuService;

    @GetMapping("/{id}")
    public Menu doSelectById(@PathVariable("id") Long id){
        return menuService.selectById(id);
    }

    @PostMapping("/insert")
    public String doInsert(@RequestBody Menu menu){
        menuService.insertMenu(menu);
        return "insert 成功！！";
    }

    @PutMapping("/update")
    public String doUpdate(@RequestBody Menu menu){
        menuService.updateMenu(menu);
        return "update 成功！！";
    }
}
