package com.rumofuture.moon.service.impl;

import com.rumofuture.moon.dao.MemberDao;
import com.rumofuture.moon.domain.Member;
import com.rumofuture.moon.domain.schema.MemberSchema;
import com.rumofuture.moon.service.MemberService;
import com.rumofuture.moon.util.ConnectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    private static final Log logger = LogFactory.getLog(MemberServiceImpl.class);

    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Member saveMember(Member member) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int id = memberDao.insertMember(connection, member);
            connection.commit();
            if (0 != id) {
                member.setId(id);
                return member;
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
    public int deleteMember(Integer id) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = memberDao.deleteMember(connection, id);
            connection.commit();
            return row;
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

        return 0;
    }

    @Override
    public Member updateMemberInfo(Member member) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = memberDao.updateMemberInfo(connection, member);
            connection.commit();
            if (1 == row) {
                return member;
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
    public Member getMemberByMobilePhoneNumber(String mobilePhoneNumber) {
        try {
            // 通过连接工厂类获得一个数据库连接
            Connection connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = memberDao.selectMemberByMobilePhoneNumber(connection, mobilePhoneNumber);
            Member member = null;
            if (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getInt(MemberSchema.Table.Cols.ID));
                member.setName(resultSet.getString(MemberSchema.Table.Cols.NAME));
                member.setMobilePhoneNumber(resultSet.getString(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                member.setLeader(null);
                member.setWorkExperience(resultSet.getInt(MemberSchema.Table.Cols.WORK_EXPERIENCE));
                member.setAnnualSalary(resultSet.getInt(MemberSchema.Table.Cols.ANNUAL_SALARY));
                member.setGraduatedFrom(resultSet.getString(MemberSchema.Table.Cols.GRADUATED_FROM));
                member.setHighestEducation(resultSet.getString(MemberSchema.Table.Cols.HIGHEST_EDUCATION));
                member.setTeamPosition(resultSet.getString(MemberSchema.Table.Cols.TEAM_POSITION));
            }
            return member;
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
        }

        return null;
    }

    @Override
    public Member getMemberById(Integer id) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = memberDao.selectMemberById(connection, id);
            Member member = null;
            if (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getInt(MemberSchema.Table.Cols.ID));
                member.setName(resultSet.getString(MemberSchema.Table.Cols.NAME));
                member.setMobilePhoneNumber(resultSet.getString(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                member.setLeader(null);
                member.setWorkExperience(resultSet.getInt(MemberSchema.Table.Cols.WORK_EXPERIENCE));
                member.setAnnualSalary(resultSet.getInt(MemberSchema.Table.Cols.ANNUAL_SALARY));
                member.setGraduatedFrom(resultSet.getString(MemberSchema.Table.Cols.GRADUATED_FROM));
                member.setHighestEducation(resultSet.getString(MemberSchema.Table.Cols.HIGHEST_EDUCATION));
                member.setTeamPosition(resultSet.getString(MemberSchema.Table.Cols.TEAM_POSITION));
                member.setCreateTime(resultSet.getTimestamp(MemberSchema.Table.Cols.CREATE_TIME).toString());
            }
            return member;
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Member> getMemberListByLeader(Integer id) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionUtil.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = memberDao.selectMemberListByLeader(connection, id);
            List<Member> memberList = new ArrayList<>();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt(MemberSchema.Table.Cols.ID));
                member.setName(resultSet.getString(MemberSchema.Table.Cols.NAME));
                member.setMobilePhoneNumber(resultSet.getString(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                member.setLeader(null);
                member.setWorkExperience(resultSet.getInt(MemberSchema.Table.Cols.WORK_EXPERIENCE));
                member.setAnnualSalary(resultSet.getInt(MemberSchema.Table.Cols.ANNUAL_SALARY));
                member.setGraduatedFrom(resultSet.getString(MemberSchema.Table.Cols.GRADUATED_FROM));
                member.setHighestEducation(resultSet.getString(MemberSchema.Table.Cols.HIGHEST_EDUCATION));
                member.setTeamPosition(resultSet.getString(MemberSchema.Table.Cols.TEAM_POSITION));
                member.setCreateTime(resultSet.getTimestamp(MemberSchema.Table.Cols.CREATE_TIME).toString());
                memberList.add(member);
            }
            return memberList;
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
