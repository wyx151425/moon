package com.rumofuture.moon.domain;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

public class Team implements Serializable {

    private Integer id;
    private String name;
    private User leader;
    private Integer memberTotal;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", memberTotal=" + memberTotal +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
