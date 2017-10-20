package com.rumofuture.moon.service;

import com.rumofuture.moon.domain.Member;

import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

public interface MemberService {
    Member saveMember(Member member);
    int deleteMember(Integer id);
    Member updateMemberInfo(Member member);
    Member getMemberByMobilePhoneNumber(String mobilePhoneNumber);
    Member getMemberById(Integer id);
    List<Member> getMemberListByLeader(Integer id);
}
