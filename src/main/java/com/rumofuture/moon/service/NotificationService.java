package com.rumofuture.moon.service;

import com.rumofuture.moon.domain.Notification;

import java.util.List;

public interface NotificationService {
    int saveNotification(Notification notification);
    int deleteNotification(Integer id);
    List<Notification> getNotificationList(Integer notifierId, Integer targetId);
    List<Notification> getNotificationList(Integer notifierId, Integer targetId, Integer type);
    List<Notification> getNotificationByTarget(Integer id);
    int getUncheckedNotificationTotal(Integer id);
}
