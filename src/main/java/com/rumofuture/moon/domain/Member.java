package com.rumofuture.moon.domain;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

public class Member implements Serializable {

    private Integer id;
    private String name;
    private String mobilePhoneNumber;
    private User leader;
    private String password;
    private Integer workExperience;
    private Integer annualSalary;
    private String graduatedFrom;
    private String highestEducation;
    private String teamPosition;
    private String createTime;

    public Member() {

    }

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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getGraduatedFrom() {
        return graduatedFrom;
    }

    public void setGraduatedFrom(String graduatedFrom) {
        this.graduatedFrom = graduatedFrom;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", workExperience=" + workExperience +
                ", annualSalary=" + annualSalary +
                ", graduatedFrom='" + graduatedFrom + '\'' +
                ", highestEducation='" + highestEducation + '\'' +
                ", teamPosition='" + teamPosition + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
