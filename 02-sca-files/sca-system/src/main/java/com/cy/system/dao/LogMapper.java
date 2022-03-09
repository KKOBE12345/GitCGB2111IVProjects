package com.cy.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.system.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
    //......
}
