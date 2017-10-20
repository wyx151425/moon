package com.rumofuture.moon.domain.schema;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

/**
 * CREATE TABLE member (
 *  id                  INT(11)      NOT NULL AUTO_INCREMENT,
 *  name                VARCHAR(255) NOT NULL,
 *  mobile_phone_number VARCHAR(11)  NOT NULL,
 *  leader_id           INT(11)      NOT NULL,
 *  work_experience     INT(2)                DEFAULT 0,
 *  annual_salary       INT(7)                DEFAULT 0,
 *  graduated_from      VARCHAR(255)          DEFAULT '',
 *  highest_education   VARCHAR(255)          DEFAULT '',
 *  team_position       VARCHAR(255)          DEFAULT '',
 *  create_time         DATETIME              DEFAULT NOW(),
 *  UNIQUE (mobile_phone_number),
 *  PRIMARY KEY (id),
 *  FOREIGN KEY (leader_id)
 *  REFERENCES user (id)
 * )
 *  ENGINE = INNODB
 *  DEFAULT CHARSET = UTF8;
 */

public class MemberSchema {
    public static final class Table {
        public static final String NAME = "member";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String MOBILE_PHONE_NUMBER = "mobile_phone_number";
            public static final String LEADER_ID = "leader_id";
            public static final String WORK_EXPERIENCE = "work_experience";
            public static final String ANNUAL_SALARY = "annual_salary";
            public static final String GRADUATED_FROM = "graduated_from";
            public static final String HIGHEST_EDUCATION = "highest_education";
            public static final String TEAM_POSITION = "team_position";
            public static final String CREATE_TIME = "create_time";
        }
    }
}
