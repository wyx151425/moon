package com.rumofuture.moon.dao.impl;

import com.rumofuture.moon.dao.NotificationDao;
import com.rumofuture.moon.domain.Notification;
import com.rumofuture.moon.domain.schema.NotificationSchema;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {

    @Override
    public int saveNotification(Connection connection, Notification notification) throws SQLException {
        String insertSql = "INSERT INTO " + NotificationSchema.Table.NAME + "(" +
                NotificationSchema.Table.Cols.NOTIFIER_ID + ", " +
                NotificationSchema.Table.Cols.TARGET_ID + ", " +
                NotificationSchema.Table.Cols.CONTENT + ", " +
                NotificationSchema.Table.Cols.IS_CHECKED + ", " +
                NotificationSchema.Table.Cols.TYPE + ") " +
                "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement statement = connection
                .prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setInt(1, notification.getNotifier().getId());
        statement.setInt(2, notification.getTarget().getId());
        statement.setString(3, notification.getContent());
        statement.setBoolean(4, notification.getChecked());
        statement.setInt(5, notification.getType());

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }

    @Override
    public int deleteNotification(Connection connection, Integer id) throws SQLException {
        String deleteSql = "DELETE FROM " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.ID + " = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSql);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    @Override
    public ResultSet getNotificationList(Connection connection, Integer notifierId, Integer targetId) throws SQLException {
        String selectSql = "SELECT * FROM " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.NOTIFIER_ID + " = ? AND " +
                NotificationSchema.Table.Cols.TARGET_ID + " = ? " +
                "ORDER BY " + NotificationSchema.Table.Cols.CREATE_TIME + " DESC";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setInt(1, notifierId);
        statement.setInt(2, targetId);
        return statement.executeQuery();
    }

    @Override
    public ResultSet getNotificationList(Connection connection, Integer notifierId, Integer targetId, Integer type) throws SQLException {
        String selectSql = "SELECT * FROM " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.NOTIFIER_ID + " = ? AND " +
                NotificationSchema.Table.Cols.TARGET_ID + " = ? AND " +
                NotificationSchema.Table.Cols.TYPE + " = ? " +
                "ORDER BY " + NotificationSchema.Table.Cols.CREATE_TIME + " DESC";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setInt(1, notifierId);
        statement.setInt(2, targetId);
        statement.setInt(3, type);
        return statement.executeQuery();
    }

    @Override
    public ResultSet getNotificationByTarget(Connection connection, Integer id) throws SQLException {
        String selectSql = "SELECT * FROM WHERE " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.TARGET_ID + " = ? " +
                "ORDER BY " + NotificationSchema.Table.Cols.CREATE_TIME + " DESC";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    @Override
    public int getUncheckedNotificationTotal(Connection connection, Integer id) throws SQLException {
        final String TOTAL = "total";
        String selectSql = "SELECT COUNT(*) AS " + TOTAL + " FROM " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.TARGET_ID + " = ? " + "AND " +
                NotificationSchema.Table.Cols.IS_CHECKED + " = " + NotificationSchema.IsCheckedVal.UNCHECKED;
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(TOTAL);
        }
        return 0;
    }
}
