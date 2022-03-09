package com.cy.system.controller;


import com.cy.system.pojo.Log;
import com.cy.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;


    @PostMapping
    public void doInsertLog(@RequestBody Log userLog){
        log.info("logController's insert name", Thread.currentThread().getName());
        logService.insertLog(userLog);
    }
}
