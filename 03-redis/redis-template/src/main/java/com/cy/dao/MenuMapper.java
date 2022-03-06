package com.cy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    //这里先使用BaseMapper自带的方法
}
