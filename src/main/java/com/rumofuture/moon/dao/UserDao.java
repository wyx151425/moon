package com.rumofuture.moon.dao;

import com.rumofuture.moon.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

public interface UserDao {
    // 注册方法
    int insertUser(Connection connection, User user) throws SQLException;
    // 修改密码方法
    int updateUserPassword(Connection connection, User user) throws SQLException;
    // 修改个人信息方法
    int updateUserInfo(Connection connection, User user) throws SQLException;
    // 登录方法
    ResultSet selectUserById(Connection connection, Integer id) throws SQLException;
    // 登录方法
    ResultSet selectUserByMobilePhoneNumber(Connection connection, String mobilePhoneNumber) throws SQLException;
}
