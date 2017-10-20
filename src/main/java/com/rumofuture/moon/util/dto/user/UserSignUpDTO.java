package com.rumofuture.moon.util.dto.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserSignUpDTO {

    @NotBlank
    @Length(min = 2)
    private String name;

    @NotBlank
    @Length(min = 11, max = 11)
    @Pattern(regexp = "^(1[0-9])\\d{9}$")
    private String mobilePhoneNumber;

    @NotBlank
    @Length(min = 6, max = 255)
    private String password;

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
}
