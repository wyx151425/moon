package com.rumofuture.moon.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by WangZhenqi on 2016/12/29.
 */

public class User implements Serializable {

    private Integer id;

    private String name;
    private String mobilePhoneNumber;
    private String password;

    private String email;
    private String avatarUrl;
    private String graduatedFrom;
    private String highestEducation;
    private String profession;
    private String teamPosition;

    private Integer age;
    private Integer workExperience;
    private Integer annualSalary;

    private String createTime;

    public User() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", graduatedFrom='" + graduatedFrom + '\'' +
                ", highestEducation='" + highestEducation + '\'' +
                ", profession='" + profession + '\'' +
                ", teamPosition='" + teamPosition + '\'' +
                ", age=" + age +
                ", workExperience=" + workExperience +
                ", annualSalary=" + annualSalary +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
