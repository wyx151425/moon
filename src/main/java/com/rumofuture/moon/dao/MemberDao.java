package com.rumofuture.moon.dao;

import com.rumofuture.moon.domain.Member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

public interface MemberDao {
    // 成员信息添加方法 insert
    int insertMember(Connection connection, Member member) throws SQLException;
    // 成员记录删除方法 delete
    int deleteMember(Connection connection, Integer id) throws SQLException;
    // 成员信息修改方法 update
    int updateMemberInfo(Connection connection, Member member) throws SQLException;
    // 成员信息获取方法 select
    ResultSet selectMemberById(Connection connection, Integer id) throws SQLException;
    // 成员信息获取方法 select
    ResultSet selectMemberByMobilePhoneNumber(Connection connection, String mobilePhoneNumber) throws SQLException;
    // 成员信息获取方法 select
    ResultSet selectMemberListByLeader(Connection connection, Integer id) throws SQLException;
}
