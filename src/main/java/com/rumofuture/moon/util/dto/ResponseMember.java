package com.rumofuture.moon.util.dto;

import com.rumofuture.moon.domain.Member;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

public class ResponseMember implements Serializable {

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;

    private int status;
    private String message;
    private Member member;

    public ResponseMember(boolean isSuccess, String message, Member member) {
        this.status = isSuccess ? SUCCESS : ERROR;
        this.message = message;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
