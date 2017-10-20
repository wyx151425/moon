package com.rumofuture.moon.util.dto.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserPasswordUpdateDTO {

    @Min(1)
    private Integer id;

    @NotBlank
    @Length(min = 6, max = 255)
    private String oldPassword;

    @NotBlank
    @Length(min = 6, max = 255)
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
