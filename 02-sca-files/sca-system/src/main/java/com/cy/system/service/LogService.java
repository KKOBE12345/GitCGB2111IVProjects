package com.cy.system.service;

import com.cy.system.pojo.Log;

public interface LogService {
    /**
     * 记录用户行为日志
     * @param log
     */
    void insertLog(Log log);
}