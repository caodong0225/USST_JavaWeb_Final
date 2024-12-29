package com.news.model;

public class User {
    private String username;
    private String password;
    private String encodedKey;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEncodedKey() {
        return encodedKey;
    }
    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }
}
