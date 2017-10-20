package com.rumofuture.moon.domain.schema;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

/**
 * CREATE TABLE notification (
 *  id          INT(11)      NOT NULL AUTO_INCREMENT,
 *  notifier_id INT(11)      NOT NULL,
 *  target_id   INT(11)      NOT NULL,
 *  content     VARCHAR(255) NOT NULL,
 *  is_checked  BOOLEAN               DEFAULT FALSE,
 *  type        INT(2)                DEFAULT 0,
 *  create_time DATETIME              DEFAULT NOW(),
 *  FOREIGN KEY (notifier_id)
 *  REFERENCES user (id),
 *  FOREIGN KEY (target_id)
 *  REFERENCES user (id),
 *  PRIMARY KEY (id)
 * )
 *  ENGINE = INNODB
 *  DEFAULT CHARSET = UTF8;
 */

public class NotificationSchema {
    public static final class Table {
        public static final String NAME = "notification";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NOTIFIER_ID = "notifier_id";
            public static final String TARGET_ID = "target_id";
            public static final String CONTENT = "content";
            public static final String IS_CHECKED = "is_checked";
            public static final String TYPE = "type";
            public static final String CREATE_TIME = "create_time";
        }
    }

    public static final class IsCheckedVal {
        public static final boolean CHECKED = true;
        public static final boolean UNCHECKED = false;
    }

    public static final class TypeVal {
        public static final int SYSTEM_NOTIFICATION = 1;
        public static final int TEAM_INVITATION = 2;
    }
}