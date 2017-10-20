package com.rumofuture.moon.service.impl;

import com.rumofuture.moon.dao.UserDao;
import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.domain.schema.UserSchema;
import com.rumofuture.moon.service.UserService;
import com.rumofuture.moon.util.ConnectionUtil;
import com.rumofuture.moon.util.mapper.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signUp(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 不要关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int id = userDao.insertUser(connection, user);
            connection.commit();
            if (0 != id) {
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    @Cacheable("mobilePhoneNumber")
    public User login(String mobilePhoneNumber) {
        System.out.println("***** login cache *****");
        try {
            // 通过连接工厂类获得一个数据库连接
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, mobilePhoneNumber);
            return ObjectMapper.getUser(resultSet);
        } catch (Exception e) {
            e.printStackTrace();    // 异常处理
        }

        return null;
    }

    @Override
    public User updateUserPassword(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = userDao.updateUserPassword(connection, user);
            connection.commit();
            if (1 == row) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();    // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User updateUserInfo(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = userDao.updateUserInfo(connection, user);
            connection.commit();
            if (1 == row) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User getUserById(Integer id) {
        try {
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultSet resultSet = userDao.selectUserById(connection, id);
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                user.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                user.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                user.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                user.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                user.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                user.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                user.setHighestEducation(resultSet.getString(UserSchema.Table.Cols.HIGHEST_EDUCATION));
                user.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                user.setCreateTime(resultSet.getTimestamp(UserSchema.Table.Cols.CREATE_TIME).toString());
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByMobilePhoneNumber(String mobilePhoneNumber) {
        try {
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, mobilePhoneNumber);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                user.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                user.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                user.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                user.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                user.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                user.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                user.setHighestEducation(resultSet.getString(UserSchema.Table.Cols.HIGHEST_EDUCATION));
                user.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
