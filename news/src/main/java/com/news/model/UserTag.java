package com.news.model;

public class UserTag {
    private String username;
    private String tag;
    private int clickCount;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if (username.length()>20) {
            throw new IllegalArgumentException("Username length must be less than 20 characters");
        }
        this.username = username;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        if (tag.length()>10) {
            throw new IllegalArgumentException("Tag length must be less than 10 characters");
        }
        this.tag = tag;
    }
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

}
