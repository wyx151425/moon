package com.rumofuture.moon.dao;

import com.rumofuture.moon.util.ConnectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)  // spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})  //上下文配置
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void logInTest() {
        Connection connection = ConnectionUtil.getInstance().getConnection();
        try {
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, "15253620357");
            resultSet.next();
            System.out.println(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
