package com.rumofuture.moon.dao.impl;

import com.rumofuture.moon.dao.MemberDao;
import com.rumofuture.moon.domain.Member;
import com.rumofuture.moon.domain.schema.MemberSchema;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

    @Override
    public int insertMember(Connection connection, Member member) throws SQLException {
        // 编辑 插入SQL语句
        String insertSql = "INSERT INTO " + MemberSchema.Table.NAME + "("
                + MemberSchema.Table.Cols.NAME + ", "
                + MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER + ", "
                + MemberSchema.Table.Cols.LEADER_ID + ", "
                + MemberSchema.Table.Cols.WORK_EXPERIENCE + ", "
                + MemberSchema.Table.Cols.ANNUAL_SALARY + ", "
                + MemberSchema.Table.Cols.GRADUATED_FROM + ", "
                + MemberSchema.Table.Cols.HIGHEST_EDUCATION + ", "
                + MemberSchema.Table.Cols.TEAM_POSITION + ") "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        // 预执行SQL语句
        PreparedStatement statement = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
        // 设置预编译的参数
        statement.setString(1, member.getName());
        statement.setString(2, member.getMobilePhoneNumber());
        statement.setInt(3, member.getLeader().getId());
        statement.setInt(4, member.getWorkExperience());
        statement.setInt(5, member.getAnnualSalary());
        statement.setString(6, member.getGraduatedFrom());
        statement.setString(7, member.getHighestEducation());
        statement.setString(8, member.getTeamPosition());

        statement.executeUpdate();

        // 返回主键
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public int deleteMember(Connection connection, Integer id) throws SQLException {
        // 编辑 删除SQL语句
        String deleteSql = "DELETE FROM " + MemberSchema.Table.NAME + " WHERE "
                + MemberSchema.Table.Cols.ID + " = ?";
        // 预执行SQL语句
        PreparedStatement statement = connection.prepareStatement(deleteSql);
        // 设置预编译的参数
        statement.setLong(1, id);
        // 返回语句执行结果
        return statement.executeUpdate();
    }

    @Override
    public int updateMemberInfo(Connection connection, Member member) throws SQLException {
        // 编辑 更新SQL语句
        String updateSql = "UPDATE " + MemberSchema.Table.NAME + " SET "
                + MemberSchema.Table.Cols.NAME + " = ?, "
                + MemberSchema.Table.Cols.WORK_EXPERIENCE + " = ?, "
                + MemberSchema.Table.Cols.ANNUAL_SALARY + " = ?, "
                + MemberSchema.Table.Cols.GRADUATED_FROM + " = ?, "
                + MemberSchema.Table.Cols.HIGHEST_EDUCATION + " = ?, "
                + MemberSchema.Table.Cols.TEAM_POSITION + " = ? " + "WHERE "
                + MemberSchema.Table.Cols.ID + " = ?";
        // 预执行SQL语句
        PreparedStatement statement = connection.prepareStatement(updateSql);
        // 设置预编译的参数
        statement.setString(1, member.getName());
        statement.setInt(2, member.getWorkExperience());
        statement.setInt(3, member.getAnnualSalary());
        statement.setString(4, member.getGraduatedFrom());
        statement.setString(5, member.getHighestEducation());
        statement.setString(6, member.getTeamPosition());
        statement.setLong(7, member.getId());
        // 返回语句执行结果
        return statement.executeUpdate();
    }

    @Override
    public ResultSet selectMemberById(Connection connection, Integer id) throws SQLException {
        // 编辑 查询SQL语句
        String selectSql = "SELECT * FROM " + MemberSchema.Table.NAME
                + " WHERE " + MemberSchema.Table.Cols.ID + " = ?";
        // 预执行SQL语句
        PreparedStatement statement = connection.prepareStatement(selectSql);
        // 设置预编译的参数
        statement.setInt(1, id);
        // 返回语句执行结果
        return statement.executeQuery();
    }

    @Override
    public ResultSet selectMemberByMobilePhoneNumber(Connection connection, String mobilePhoneNumber) throws SQLException {
        // 编辑 查询SQL语句
        String selectSql = "SELECT * FROM " + MemberSchema.Table.NAME
                + " WHERE " + MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER + " = ?";
        // 预执行SQL语句
        PreparedStatement statement = connection.prepareStatement(selectSql);
        // 设置预编译的参数
        statement.setString(1, mobilePhoneNumber);
        // 返回语句执行结果
        return statement.executeQuery();
    }

    @Override
    public ResultSet selectMemberListByLeader(Connection conn, Integer id) throws SQLException {
        // 编辑 查询SQL语句
        String selectSql = "SELECT * FROM " + MemberSchema.Table.NAME
                + " WHERE " + MemberSchema.Table.Cols.LEADER_ID + " = ?";
        // 预执行SQL语句
        PreparedStatement statement = conn.prepareStatement(selectSql);
        // 设置预编译的参数
        statement.setInt(1, id);
        // 返回语句执行结果
        return statement.executeQuery();
    }
}
