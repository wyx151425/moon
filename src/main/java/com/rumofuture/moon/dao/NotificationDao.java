package com.rumofuture.moon.dao;

import com.rumofuture.moon.domain.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface NotificationDao {
    int saveNotification(Connection connection, Notification notification) throws SQLException;
    int deleteNotification(Connection connection, Integer id) throws SQLException;
    ResultSet getNotificationList(Connection connection, Integer notifierId, Integer targetId) throws SQLException;
    ResultSet getNotificationList(Connection connection, Integer notifierId, Integer targetId, Integer type) throws SQLException;
    ResultSet getNotificationByTarget(Connection connection, Integer id) throws SQLException;
    int getUncheckedNotificationTotal(Connection connection, Integer id) throws SQLException;
}
