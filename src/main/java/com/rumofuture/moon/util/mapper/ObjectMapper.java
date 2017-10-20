package com.rumofuture.moon.util.mapper;

import com.rumofuture.moon.domain.Notification;
import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.domain.schema.NotificationSchema;
import com.rumofuture.moon.domain.schema.UserSchema;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

public class ObjectMapper {

    public static User getUser(ResultSet resultSet) throws SQLException {
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
    }

    public static Notification getNotification(ResultSet resultSet) throws SQLException {
        Notification notification = new Notification();
        notification.setId(resultSet.getInt(NotificationSchema.Table.Cols.ID));
        int notifierId = resultSet.getInt(NotificationSchema.Table.Cols.NOTIFIER_ID);
        User notifier = new User();
        notifier.setId(notifierId);
        notification.setNotifier(notifier);
        int targetId = resultSet.getInt(NotificationSchema.Table.Cols.TARGET_ID);
        User target = new User();
        target.setId(targetId);
        notification.setTarget(target);
        notification.setContent(resultSet.getString(NotificationSchema.Table.Cols.CONTENT));
        notification.setChecked(resultSet.getBoolean(NotificationSchema.Table.Cols.IS_CHECKED));
        notification.setType(resultSet.getInt(NotificationSchema.Table.Cols.TYPE));
        notification.setCreateTime(String.valueOf(resultSet.getTimestamp(NotificationSchema.Table.Cols.CREATE_TIME)));
        return notification;
    }
}
