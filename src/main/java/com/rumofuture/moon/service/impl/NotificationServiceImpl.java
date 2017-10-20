package com.rumofuture.moon.service.impl;

import com.rumofuture.moon.dao.NotificationDao;
import com.rumofuture.moon.domain.Notification;
import com.rumofuture.moon.service.NotificationService;
import com.rumofuture.moon.util.ConnectionUtil;
import com.rumofuture.moon.util.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDao notificationDao;

    @Autowired
    public NotificationServiceImpl(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }


    @Override
    public int saveNotification(Notification notification) {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            int id = notificationDao.saveNotification(connection, notification);
            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public int deleteNotification(Integer id) {
        return 0;
    }

    @Override
    public List<Notification> getNotificationList(Integer notifierId, Integer targetId) {
        return null;
    }

    @Override
    public List<Notification> getNotificationList(Integer notifierId, Integer targetId, Integer type) {
        try {
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultSet resultSet = notificationDao.getNotificationList(connection, notifierId, targetId, type);
            List<Notification> notificationList = new ArrayList<>();
            while (resultSet.next()) {
                Notification notification = ObjectMapper.getNotification(resultSet);
                notificationList.add(notification);
            }
            return notificationList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> getNotificationByTarget(Integer id) {
        return null;
    }

    @Override
    public int getUncheckedNotificationTotal(Integer id) {
        try {
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            return notificationDao.getUncheckedNotificationTotal(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
