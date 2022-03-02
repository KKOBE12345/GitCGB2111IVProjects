package com.cy;


import com.cy.system.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class DaoSourceTest {

    /**
     * 我们通过注入这个对象
     * 获取数据库的连接池依赖*/
    @Autowired
    private DataSource dataSource;//HikariDataSource

    @Autowired
    private UserMapper userMapper;

    @Test
    void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }

    @Test
    void testMenu(){
        List<String> userPermissions = userMapper.selectUserPermissions(1L);
        System.out.println(userPermissions);
    }
}
