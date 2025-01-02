package com.news.model;

import com.news.util.DateUtil;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date birthday;
    private String sex;
    private String career;
    private String country;
    private String educationBackground;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() > 20) {
            throw new IllegalArgumentException("Username length must be less than 20 characters");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if (password.length() > 20) {
            throw new IllegalArgumentException("Password length must be less than 20 characters");
        }
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public int getAge() {
        return DateUtil.CalAge(birthday);
    }
}
