package com.cy.system.service;

import com.cy.system.dao.LogMapper;
import com.cy.system.pojo.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     *  @Async 描述的方法为一个异步切入点方法,
     *  此方法在执行时,可以从spring自带的线程池
     *  中获取一个线程来执行这个方法,而不是使用
     *  web服务池中的线程.这样web服务中的线程
     *  就可以去服务于新的客户端请求服务了。
     *  但是这个异步任务要启动，需要在启动类
     *  或配置上添加@EnableAsync注解
     */

    @Override
    @Async
    public void insertLog(Log userlog) {

        logMapper.insert(userlog);
    }
}