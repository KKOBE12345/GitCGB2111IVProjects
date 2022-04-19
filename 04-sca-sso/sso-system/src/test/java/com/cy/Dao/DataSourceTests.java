package com.cy.Dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: GitCGB2111IVProjects
 * @description
 * @author: George
 * @create: 2022-04-17 13:29
 **/
@SpringBootTest
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void testGetConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
