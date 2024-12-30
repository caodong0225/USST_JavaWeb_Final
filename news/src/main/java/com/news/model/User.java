package com.news.model;

public class User {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if (username.length()>20) {
            throw new IllegalArgumentException("Username length must be less than 20 characters");
        }
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {

        if (password.length()>20) {
            throw new IllegalArgumentException("Password length must be less than 20 characters");
        }
        this.password = password;
    }
}
