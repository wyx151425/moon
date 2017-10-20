package com.rumofuture.moon.dao.impl;

import com.rumofuture.moon.dao.UserDao;
import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.domain.schema.UserSchema;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public int insertUser(Connection conn, User user) throws SQLException {
        // PreparedStatement是JDBC用来执行SQL查询的API之一
        String insertSql = "INSERT INTO "
                + UserSchema.Table.NAME + "("
                + UserSchema.Table.Cols.NAME + ", "
                + UserSchema.Table.Cols.MOBILE_PHONE_NUMBER + ", "
                + UserSchema.Table.Cols.PASSWORD + ") "
                + "VALUES(?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getName());
        statement.setString(2, user.getMobilePhoneNumber());
        statement.setString(3, user.getPassword());

        statement.executeUpdate(); // 执行数据库语句的函数

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public int updateUserPassword(Connection conn, User user) throws SQLException {
        String updateSql = "UPDATE " + UserSchema.Table.NAME + " SET "
                + UserSchema.Table.Cols.PASSWORD + " = ? WHERE "
                + UserSchema.Table.Cols.ID + " = ?";
        PreparedStatement statement = conn.prepareStatement(updateSql);
        statement.setString(1, user.getPassword());
        statement.setInt(2, user.getId());
        return statement.executeUpdate();
    }

    @Override
    public int updateUserInfo(Connection conn, User user) throws SQLException {
        String updateSql = "UPDATE " + UserSchema.Table.NAME + " SET "
                + UserSchema.Table.Cols.NAME + " = ?, "
                + UserSchema.Table.Cols.WORK_EXPERIENCE + " = ?, "
                + UserSchema.Table.Cols.ANNUAL_SALARY + " = ?, "
                + UserSchema.Table.Cols.GRADUATED_FROM + " = ?, "
                + UserSchema.Table.Cols.HIGHEST_EDUCATION + " = ?, "
                + UserSchema.Table.Cols.TEAM_POSITION + " = ? " + "WHERE "
                + UserSchema.Table.Cols.ID + " = ?";
        PreparedStatement statement = conn.prepareStatement(updateSql);

        statement.setString(1, user.getName());
        statement.setInt(2, user.getWorkExperience());
        statement.setInt(3, user.getAnnualSalary());
        statement.setString(4, user.getGraduatedFrom());
        statement.setString(5, user.getHighestEducation());
        statement.setString(6, user.getTeamPosition());
        statement.setInt(7, user.getId());
        return statement.executeUpdate();
    }

    @Override
    public ResultSet selectUserById(Connection connection, Integer id) throws SQLException {
        String selectSql = "SELECT * FROM " + UserSchema.Table.NAME
                + " WHERE " + UserSchema.Table.Cols.ID + " = ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        //设置预编译的参数
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    @Override
    public ResultSet selectUserByMobilePhoneNumber(Connection conn, String mobilePhoneNumber) throws SQLException {
        String selectSql = "SELECT * FROM " + UserSchema.Table.NAME
                + " WHERE " + UserSchema.Table.Cols.MOBILE_PHONE_NUMBER + " = ?";
        PreparedStatement statement = conn.prepareStatement(selectSql);
        //设置预编译的参数
        statement.setString(1, mobilePhoneNumber);
        return statement.executeQuery();
    }
}
