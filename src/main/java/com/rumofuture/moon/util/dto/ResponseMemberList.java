package com.rumofuture.moon.util.dto;

import com.rumofuture.moon.domain.Member;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

public class ResponseMemberList implements Serializable {

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;

    private int status;
    private String message;
    private List<Member> memberList;

    public ResponseMemberList(boolean isSuccess, String message, List<Member> memberList) {
        this.status = isSuccess ? SUCCESS : ERROR;
        this.message = message;
        this.memberList = memberList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
